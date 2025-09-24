package unam.fciencias.modeladoyprogramacion.restfultapi;

import java.util.Optional;

/**
 * TinyURLService
 */
public interface TinyURLService {

    String createShortURL(String longURL);

    Optional<String> findURL(String shortCode);
}
