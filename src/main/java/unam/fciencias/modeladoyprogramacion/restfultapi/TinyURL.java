package unam.fciencias.modeladoyprogramacion.restfultapi;

import lombok.Data;

@Data
public class TinyURL {
    private long id;
    private String longUrl;
    private String shortURL;

    public TinyURL() {
    }

    public TinyURL(long id, String longUrl, String shortURL) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortURL = shortURL;
    }
}
