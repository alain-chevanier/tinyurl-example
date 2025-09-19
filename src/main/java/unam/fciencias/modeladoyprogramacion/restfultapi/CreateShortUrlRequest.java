package unam.fciencias.modeladoyprogramacion.restfultapi;

import lombok.Data;

@Data
public class CreateShortUrlRequest {

    private String url;

    public CreateShortUrlRequest() {
    }

    public CreateShortUrlRequest(String longUrl) {
        this.url = longUrl;
    }
}
