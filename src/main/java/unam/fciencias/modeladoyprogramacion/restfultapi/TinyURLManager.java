package unam.fciencias.modeladoyprogramacion.restfultapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
/**
 * TinyURLManager
 */
@Service
public class TinyURLManager implements TinyURLService {
    public static final int NUMERIC_BASE = 36;

    private int serverPort;
    private String serverHost;

    private static long nextId = 450000;
    private static List<TinyURL> shortenedUrls = new ArrayList<>();

    public TinyURLManager(@Value("${server.port:8080}") int port,
                          @Value("${server.address:localhost}") String host) {
        this.serverPort = port;
        this.serverHost = host;
    }

    @Override
    public String createShortURL(String longURL) {
        // Check if the long URL has already been shortened
        Optional<TinyURL> existing = findByLongURL(longURL);
        if (existing.isPresent()) {
            return existing.get().getShortURL();
        }

        // Generate a new ID
        long myId = nextId++;
        // Convert the ID to a base-36 string
        String hashedId = Long.toString(myId, NUMERIC_BASE);
        String shortURL = getBaseURL() + "/tinyurl/" + hashedId;
        var tinyURL = new TinyURL(myId, longURL, shortURL);
        shortenedUrls.add(tinyURL);
        return shortURL;
    }

    private Optional<TinyURL> findByLongURL(String longURL) {
        return shortenedUrls.stream()
            .filter(tinyURL -> tinyURL.getLongUrl().toLowerCase().equals(longURL.toLowerCase()))
            .findFirst();
    }

    @Override
    public Optional<String> findURL(String shortCode) {
        long myId = Long.parseLong(shortCode, NUMERIC_BASE);
        return shortenedUrls.stream()
            .filter(tinyURL -> tinyURL.getId() == myId)
            .map(TinyURL::getLongUrl)
            .findFirst();
    }

    private String getBaseURL() {
        return "http://" + serverHost + ":" + serverPort;
    }
}
