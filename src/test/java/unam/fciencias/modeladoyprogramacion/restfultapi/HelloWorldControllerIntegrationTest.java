package unam.fciencias.modeladoyprogramacion.restfultapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloWorldControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnHelloWorldWhenCallingRootEndpoint() {
        // Given
        String url = "http://localhost:" + port + "/";

        // When
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Hello, World!");
    }

    @Test
    void shouldReturnCorrectContentTypeForRootEndpoint() {
        // Given
        String url = "http://localhost:" + port + "/";

        // When
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getContentType().toString()).contains("text/plain");
    }

    @Test
    void shouldReturn404ForNonExistentEndpoint() {
        // Given
        String url = "http://localhost:" + port + "/nonexistent";

        // When
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldHandleMultipleRequestsToRootEndpoint() {
        // Given
        String url = "http://localhost:" + port + "/";

        // When & Then - Make multiple requests
        for (int i = 0; i < 5; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isEqualTo("Hello, World!");
        }
    }
}
