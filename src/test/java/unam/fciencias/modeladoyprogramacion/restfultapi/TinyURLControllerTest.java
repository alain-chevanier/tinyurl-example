package unam.fciencias.modeladoyprogramacion.restfultapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(TinyURLController.class)
class TinyURLControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TinyURLService tinyURLServiceMock;

    @Test
    void shouldReturnCreatedWithMockShortUrlWhenCreatingShortUrl() throws Exception {
        String requestBody = "{\"url\":\"https://example.com/some/very/long/url\"}";

        when(tinyURLServiceMock.createShortURL("https://example.com/some/very/long/url"))
                .thenReturn("http://short.url/abc123");

        mockMvc.perform(post("/tinyurl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated()) // 201 Created
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.shortUrl").value("http://short.url/abc123"));
    }

    @Test
    void shouldRedirectToHardcodedLongUrlWhenAccessingShortUrl() throws Exception {
        String expectedLongUrl = "http://localhost:8080/some/very/long/path";

        when(tinyURLServiceMock.findURL("abc123")).thenReturn(Optional.of(expectedLongUrl));

        mockMvc.perform(get("/tinyurl/{shortCode}", "abc123"))
                .andExpect(status().isFound()) // 302 Found
                .andExpect(header().string("Location", expectedLongUrl))
                .andExpect(redirectedUrl(expectedLongUrl));
    }

    @Test
    void shouldReturnNotFoundWhenShortUrlDoesNotExist() throws Exception {
        when(tinyURLServiceMock.findURL("nonexistent")).thenReturn(Optional.empty());

        mockMvc.perform(get("/tinyurl/{shortCode}", "nonexistent"))
                .andExpect(status().isNotFound()); //404 Not Found
    }
}
