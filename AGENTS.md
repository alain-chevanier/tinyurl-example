For agents working in this repo (Spring Boot 3, Java 17).
Build: ./mvnw clean verify; Run: ./mvnw spring-boot:run
Tests: ./mvnw test; one class: ./mvnw -Dtest=HelloWorldControllerTest test
One method: ./mvnw -Dtest=HelloWorldControllerTest#shouldReturnHelloWorldWhenCallingRootEndpoint test
Jar: ./mvnw -DskipTests package; run: java -jar target/restfultapi-0.0.1-SNAPSHOT.jar
Lint/format: No formatter plugin configured; format via IDE consistently.
Language/versions: Java 17; Spring Boot parent 3.5.5; JUnit 5.
Imports: no wildcard imports; group java/javax, third-party, project; static imports after normal imports.
Formatting: 4-space indent, ~120-char line length, trailing newline; braces on same line.
Naming: classes PascalCase; methods/vars camelCase; constants UPPER_SNAKE; packages lowercase.
Types: prefer interfaces for injection; make fields final where possible; avoid raw types.
Nullability: avoid null; prefer Optional for returns; validate inputs with @Validated/@NotNull.
Controllers: use @RestController; return ResponseEntity<T>; keep endpoints small and pure.
Error handling: centralize with @ControllerAdvice + @ExceptionHandler; map to correct HTTP status; no stack traces in responses.
Logging: use slf4j LoggerFactory; avoid System.out; include context, not PII.
Exceptions: throw specific exceptions; never swallow; wrap and rethrow with cause.
Tests: unit with @WebMvcTest/MockMvc; integration with @SpringBootTest(RANDOM_PORT); names like shouldXWhenY.
Data: default H2 in-memory; tests must not rely on external services or fixed ports.
Commits/PRs: keep changes small; include tests; run ./mvnw clean verify locally.
Structure: code in src/main/java; tests in src/test/java; app name restfultapi.
