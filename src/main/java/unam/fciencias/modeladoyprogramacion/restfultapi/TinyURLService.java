package unam.fciencias.modeladoyprogramacion.restfultapi;

/**
 * Service responsible for generating and resolving TinyURL mappings.
 *
 * This is a scaffold with method signatures only; real logic will be added later.
 */
public interface TinyURLService {

    /**
     * Generate a short code representing the provided long URL.
     *
     * @param longUrl the original long URL to shorten
     * @return a short code (e.g., "abc123") representing the mapping
     */
    String createShortURL(String longUrl);

    /**
     * Retrieve the original long URL for the given short code.
     *
     * @param shortCode the short code to resolve
     * @return the original long URL
     */
    String getLongUrl(String shortCode);
}
