package pl.bart.temperaturelog.utilities;

import org.springframework.stereotype.Component;
import pl.bart.temperaturelog.models.Station;
import pl.bart.temperaturelog.repositories.StationRepository;

@Component
public class ApiKeyAuth {
    private final StationRepository stationRepository;

    public ApiKeyAuth(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public boolean authenticate(Long stationId, String apiKey) {
        Station station = stationRepository.findById(stationId).orElse(null);
        if (station != null) {
            if (station.getApiKey().equals(apiKey) || System.getenv("MASTER_KEY").equals(apiKey)) {
                return true;
            }
        }
        return false;
    }
}
