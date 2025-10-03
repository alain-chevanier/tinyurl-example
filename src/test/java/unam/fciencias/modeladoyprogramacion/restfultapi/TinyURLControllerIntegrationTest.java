package unam.fciencias.modeladoyprogramacion.restfultapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(properties = { "server.address=localhost", "server.port=7777" },
                webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TinyURLControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldRedirectToOriginalUrlfgWhenShortUrlIsAccessed() {
        // Given - first create a short URL
        String longUrl = "http://localhost:" + port + "/"; // local path to avoid external calls

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateShortUrlRequest> request = new HttpEntity<>(new CreateShortUrlRequest(longUrl), headers);

        String createUrl = "http://localhost:" + port + "/tinyurl";
        ResponseEntity<CreateShortUrlResponse> createResponse = restTemplate.postForEntity(createUrl, request,
                CreateShortUrlResponse.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        String shortUrl = createResponse.getBody().getShortUrl();

        // When - access the short URL
        ResponseEntity<String> response = restTemplate.getForEntity(shortUrl,String.class);

        // Then - should redirect to the original URL
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Hello, World!");
    }
}
