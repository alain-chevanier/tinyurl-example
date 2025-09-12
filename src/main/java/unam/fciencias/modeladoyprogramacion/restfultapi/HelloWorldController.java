package unam.fciencias.modeladoyprogramacion.restfultapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorldController
*/
@RestController
public class HelloWorldController {

    // create a get endpoint that returns "Hello, World!" when accessed at the root URL ("/")
    @GetMapping("/")
    public String helloWorld() {
        return "Hello, World!";
    }
}
