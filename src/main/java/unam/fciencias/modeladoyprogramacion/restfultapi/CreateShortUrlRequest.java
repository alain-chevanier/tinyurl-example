package unam.fciencias.modeladoyprogramacion.restfultapi;

import lombok.Data;

/**
 * Request body for creating a short URL from a long URL.
 */
@Data
public class CreateShortUrlRequest {

    private String url;

    public CreateShortUrlRequest() {
    }

    public CreateShortUrlRequest(String longUrl) {
        this.url = longUrl;
    }
}
