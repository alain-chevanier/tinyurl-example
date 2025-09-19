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

    private final TinyURLService tinyURLService;

    public TinyURLController(TinyURLService tinyURLService) {
        this.tinyURLService = tinyURLService;
    }

    @PostMapping(value = "/tinyurl")
    public ResponseEntity<CreateShortUrlResponse>
        createShortUrl(@RequestBody CreateShortUrlRequest request) {
        String shortCode = tinyURLService.createShortURL(request.getUrl());
        var response = new CreateShortUrlResponse("http://short.url/" + shortCode);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping(value = "/tinyurl/{shortCode}")
    public ResponseEntity<Void> redirectToLongUrl(@PathVariable String shortCode) {
        String longUrl = tinyURLService.getLongUrl(shortCode);
        return ResponseEntity
            .status(HttpStatus.FOUND)
            .header("Location", longUrl)
            .build();
    }
}
