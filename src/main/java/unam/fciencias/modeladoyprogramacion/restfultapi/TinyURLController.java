package unam.fciencias.modeladoyprogramacion.restfultapi;

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
    private TinyURLService tinyURLService;

    public TinyURLController(TinyURLService tinyURLService) {
        this.tinyURLService = tinyURLService;
    }

    @PostMapping(value = "/tinyurl")
    public ResponseEntity<CreateShortUrlResponse>
        createShortUrl(@RequestBody CreateShortUrlRequest request) {

        var shortURL = tinyURLService.createShortURL(request.getUrl());
        var response = new CreateShortUrlResponse(shortURL);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping(value = "/tinyurl/{shortCode}")
    public ResponseEntity<Void>
        redirectToLongUrl(@PathVariable String shortCode) {
        var longURL = tinyURLService.findURL(shortCode);
        if (longURL.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND)
                                 .header("Location", longURL.get())
                                 .build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                              "Short URL not found");
        }
    }
}
