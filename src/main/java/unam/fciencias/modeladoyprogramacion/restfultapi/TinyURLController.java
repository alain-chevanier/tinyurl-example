package unam.fciencias.modeladoyprogramacion.restfultapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TinyURLController {
    private static final int NUMERIC_BASE = 36;
    private static final String BASE_URL = "http://localhost:8080";

    private static long nextId = 450000;
    private static List<TinyURL> shortenedUrls = new ArrayList<>();

    @PostMapping(value = "/tinyurl")
    public ResponseEntity<CreateShortUrlResponse>
        createShortUrl(@RequestBody CreateShortUrlRequest request) {

        var shortURL = createShortURLAux(request.getUrl());
        var response = new CreateShortUrlResponse(shortURL);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    private String createShortURLAux(String longURL) {
        long myId = nextId++;
        // Convert the ID to a base-36 string
        String hashedId = Long.toString(myId, NUMERIC_BASE);
        String shortURL = BASE_URL + "/tinyurl/" + hashedId;
        var tinyURL = new TinyURL(myId, longURL, shortURL);
        shortenedUrls.add(tinyURL);
        return shortURL;
    }

    @GetMapping(value = "/tinyurl/{shortCode}")
    public ResponseEntity<Void>
        redirectToLongUrl(@PathVariable String shortCode) {
        //Convert the base-36 string back to a long ID
        long myId = Long.parseLong(shortCode, NUMERIC_BASE);
        var longURL = findURL(myId);
        if (longURL.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND)
                                 .header("Location", longURL.get())
                                 .build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                              "Short URL not found");
        }
    }

    private Optional<String> findURL(long tinyURLID) {
        return shortenedUrls.stream()
            .filter(tinyURL -> tinyURL.getId() == tinyURLID)
            .map(TinyURL::getLongUrl)
            .findFirst();
    }
}
