package unam.fciencias.modeladoyprogramacion.restfultapi;

import lombok.Data;

/**
 * Response body for the short URL creation endpoint.
 */
@Data
public class CreateShortUrlResponse {

    private String shortUrl;

    public CreateShortUrlResponse() {
    }

    public CreateShortUrlResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
