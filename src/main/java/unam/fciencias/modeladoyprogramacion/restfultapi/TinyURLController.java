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
        var baseDomain = "http://localhost:8080";
        long myId = nextId++;
        String hashedString = Long.toString(myId, 36);
        String shortURL = baseDomain + "/tinyurl/" + hashedString;
        var tinyURL = new TinyURL(myId,
                                  longURL,
                                  shortURL);
        shortenedUrls.add(tinyURL);
        return shortURL;
    }

    @GetMapping(value = "/tinyurl/{shortCode}")
    public ResponseEntity<Void>
        redirectToLongUrl(@PathVariable String shortCode) {

        long myId = Long.parseLong(shortCode, 36);
        var longURL = findURL(myId);
        if (longURL.isPresent()) {
            return ResponseEntity
                .status(HttpStatus.FOUND)
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
