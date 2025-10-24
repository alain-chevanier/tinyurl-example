package unam.fciencias.modeladoyprogramacion.restfultapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TinyURLManagerTest
*/
public class TinyURLManagerTest {
    TinyURLService service;

    @BeforeEach
    public void setUp() {
        var host = "localhost";
        var port = 8080;
        service = new TinyURLManager(port, host);
    }

    @Test
    public void testCreateShortURL() {
        // data setup
        String longURL = "http://localhost:8080/some/very/long/path";

        // call the method
        String shortURL = service.createShortURL(longURL);

        // assert behavior
        // http://localhost:8080/tinyurl/asdf123
        assertThat(shortURL).startsWith("http://localhost:8080/tinyurl/");
        String shortCode = shortURL.substring(shortURL.lastIndexOf("/") + 1);

        // shortCode: "asdf123"
        assertThat(shortCode).matches("^[0-9a-z]+$");
    }

    @Test
    public void testCreateShortURLUnique() {
        String longURL = "http://localhost:8080/some/very/long/path1";
        String shortURL1 = service.createShortURL(longURL);
        String shortURL2 = service.createShortURL(longURL);
        assertThat(shortURL1).isEqualTo(shortURL2);
    }

    @Test
    public void testFindURL() {
        String longURL = "http://localhost:8080/some/very/long/path2";
        String shortURL = service.createShortURL(longURL);
        String shortCode = shortURL.substring(shortURL.lastIndexOf("/") + 1);

        var foundURL = service.findURL(shortCode);
        assertThat(foundURL).isPresent();
        assertThat(foundURL.get()).isEqualTo(longURL);
    }

    // let's test that findURL returns empty for non-existent short code
    @Test
    public void testFindURLNotFound() {
        var foundURL = service.findURL("nonexistent");
        assertThat(foundURL).isNotPresent();
    }
}
