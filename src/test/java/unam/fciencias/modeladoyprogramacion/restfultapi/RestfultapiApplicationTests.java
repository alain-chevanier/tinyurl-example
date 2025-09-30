package unam.fciencias.modeladoyprogramacion.restfultapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "server.address=localhost", "server.port=7777" },
                webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RestfultapiApplicationTests {

	@Test
	void contextLoads() {
	}

}
