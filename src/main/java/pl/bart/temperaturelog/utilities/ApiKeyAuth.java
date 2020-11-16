package pl.bart.temperaturelog.utilities;

import org.springframework.stereotype.Component;
import pl.bart.temperaturelog.exceptions.StationNotExistingException;
import pl.bart.temperaturelog.models.Station;
import pl.bart.temperaturelog.repositories.StationRepository;
import pl.bart.temperaturelog.security.Credentials;

@Component
public class ApiKeyAuth {
    private final StationRepository stationRepository;

    public ApiKeyAuth(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public boolean authenticate(Credentials credentials){
        Long stationId = credentials.getId();
        String apiKey = credentials.getApiKey();
        Station station = stationRepository.findById(stationId).orElse(null);
        boolean stationExists = stationRepository.existsById(stationId);
        if (stationExists) {
            if (station.getApiKey().equals(apiKey) || System.getenv("MASTER_KEY").equals(apiKey)) {
                return true;
            }
        }
        else throw new StationNotExistingException();
        return false;
    }
}
