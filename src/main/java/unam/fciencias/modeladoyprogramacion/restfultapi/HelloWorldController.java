package unam.fciencias.modeladoyprogramacion.restfultapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * HelloWorldController
*/
@RestController
public class HelloWorldController {

    private static final List<String> MESSAGES = List.of(
            "Hello, World!",
            "Hola, Mundo!",
            "Bonjour, le monde!",
            "Hallo, Welt!",
            "Ciao, mondo!"
    );

    private static final Random RANDOM = new Random();

    @GetMapping("/")
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping("/random")
    public Map<String, String> randomMessage() {
        String randomMessage = MESSAGES.get(RANDOM.nextInt(MESSAGES.size()));
        Map<String, String> response = new HashMap<>();
        response.put("message", randomMessage);
        return response;
    }

    @GetMapping("/this/is/a/pretty/long/path/that/we/might/want/to/shorten")
    public Map<String, String> longURL() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "this message should appear at a long URL path");
        return response;
    }
}
