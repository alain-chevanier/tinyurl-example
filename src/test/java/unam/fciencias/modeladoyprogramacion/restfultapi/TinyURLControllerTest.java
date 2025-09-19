package unam.fciencias.modeladoyprogramacion.restfultapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TinyURLController.class)
class TinyURLControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TinyURLService tinyURLService;

    @Test
    void shouldReturnCreatedWithMockShortUrlWhenCreatingShortUrl() throws Exception {
        String longUrl = "https://example.com/some/very/long/url";
        String requestBody = "{\"url\":\"" + longUrl + "\"}";
        when(tinyURLService.createShortURL(longUrl)).thenReturn("abc123");

        mockMvc.perform(post("/tinyurl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.shortUrl").value("http://short.url/abc123"));

        verify(tinyURLService).createShortURL(longUrl);
    }

    @Test
    void shouldRedirectToHardcodedLongUrlWhenAccessingShortUrl() throws Exception {
        String expectedLongUrl = "http://localhost:8080";
        when(tinyURLService.getLongUrl("abc123")).thenReturn(expectedLongUrl);

        mockMvc.perform(get("/tinyurl/{shortCode}", "abc123"))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", expectedLongUrl))
                .andExpect(redirectedUrl(expectedLongUrl));

        verify(tinyURLService).getLongUrl("abc123");
    }
}
