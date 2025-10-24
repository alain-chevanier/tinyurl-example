package unam.fciencias.modeladoyprogramacion.restfultapi;

import lombok.Data;

@Data
public class TinyURL {
    private long id;
    private String longUrl;
    private String shortUrl;

    private TinyURL() {
    }

    public static TinyURLBuilder builder() {
        return new TinyURLBuilder();
    }

    public static class TinyURLBuilder implements Builder<TinyURL> {
        private long id;
        private String longUrl;
        private String shortUrl;

        public TinyURLBuilder id(long id) {
            this.id = id;
            return this;
        }

        public TinyURLBuilder longUrl(String longUrl) {
            this.longUrl = longUrl;
            return this;
        }

        public TinyURLBuilder shortUrl(String shortURL) {
            this.shortUrl = shortURL;
            return this;
        }

        public TinyURL build() {
            var tinyURL = new TinyURL();
            tinyURL.id = this.id;
            tinyURL.longUrl = this.longUrl;
            tinyURL.shortUrl = this.shortUrl;
            return tinyURL;
        }
    }
}
