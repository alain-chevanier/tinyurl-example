package unam.fciencias.modeladoyprogramacion.restfultapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloWorldController.class)
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnHelloWorldWhenCallingRootEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    void shouldReturnRandomMessage() throws Exception {
        mockMvc.perform(get("/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void shouldReturnMessageForLongPath() throws Exception {
        mockMvc.perform(get("/this/is/a/pretty/long/path/that/we/might/want/to/shorten"))
                .andExpect(status().isOk()) // 2** : 200 OK, 201 Created, 204 No Content
                .andExpect(jsonPath("$.message").value("this message should appear at a long URL path"))
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void shouldReturn404ForNonExistentEndpoint() throws Exception {
        mockMvc.perform(get("/nonexistent"))
                .andExpect(status().isNotFound());
    }
}
