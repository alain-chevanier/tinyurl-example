package unam.fciencias.modeladoyprogramacion.restfultapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TinyURLController {

    @PostMapping(value = "/tinyurl")
    public ResponseEntity<CreateShortUrlResponse>
        createShortUrl(@RequestBody CreateShortUrlRequest request) {
        // Mocked response; real logic will be implemented later.
        var response = new CreateShortUrlResponse("http://short.url/abc123");
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping(value = "/tinyurl/{shortCode}")
    public ResponseEntity<Void> redirectToLongUrl(@PathVariable String shortCode) {
        // Mocked redirect; real logic will be implemented later.
        var hardcodedLongUrl = "https://example.com/original/very/long/url";
        return ResponseEntity
            .status(HttpStatus.FOUND)
            .header("Location", hardcodedLongUrl)
            .build();
    }
}
