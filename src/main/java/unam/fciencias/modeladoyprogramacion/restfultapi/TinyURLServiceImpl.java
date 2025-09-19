package unam.fciencias.modeladoyprogramacion.restfultapi;

import org.springframework.stereotype.Service;

@Service
public class TinyURLServiceImpl implements TinyURLService {

    @Override
    public String createShortURL(String longUrl) {
        // TODO: implement generation logic
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getLongUrl(String shortCode) {
        // TODO: implement lookup logic
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
