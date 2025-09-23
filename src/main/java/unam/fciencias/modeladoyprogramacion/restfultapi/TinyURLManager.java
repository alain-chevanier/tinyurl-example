package unam.fciencias.modeladoyprogramacion.restfultapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
/**
 * TinyURLManager
 */
@Service
public class TinyURLManager implements TinyURLService {
    public static final int NUMERIC_BASE = 36;
    private static final String BASE_URL = "http://localhost:8080";

    private static long nextId = 450000;
    private static List<TinyURL> shortenedUrls = new ArrayList<>();

    @Override
    public String createShortURL(String longURL) {
        long myId = nextId++;
        // Convert the ID to a base-36 string
        String hashedId = Long.toString(myId, NUMERIC_BASE);
        String shortURL = BASE_URL + "/tinyurl/" + hashedId;
        var tinyURL = new TinyURL(myId, longURL, shortURL);
        shortenedUrls.add(tinyURL);
        return shortURL;
    }

    @Override
    public Optional<String> findURL(String shortCode) {
        long myId = Long.parseLong(shortCode, TinyURLManager.NUMERIC_BASE);
        return shortenedUrls.stream()
            .filter(tinyURL -> tinyURL.getId() == myId)
            .map(TinyURL::getLongUrl)
            .findFirst();
    }
}
