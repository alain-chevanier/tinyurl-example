package unam.fciencias.modeladoyprogramacion.restfultapi;

import lombok.Data;

@Data
public class CreateShortUrlResponse {

    private String shortUrl;

    public CreateShortUrlResponse() {
    }

    public CreateShortUrlResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
