package pl.bart.temperaturelog.utilities;

import org.springframework.stereotype.Component;
import pl.bart.temperaturelog.models.Station;
import pl.bart.temperaturelog.repositories.StationRepository;


@Component
public class ApiCallNumberValidator {
    private final StationRepository stationRepository;
    private final static int MAX_NUMBER_OF_MEASUREMENTS = 750;

    public ApiCallNumberValidator(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public boolean validateNumberOfMeasurements(Long stationId) {
        Station station = stationRepository.findById(stationId).orElse(null);
        if (station != null) {
            if (station.getNumberOfMeasurements() < MAX_NUMBER_OF_MEASUREMENTS) return true;
            else return false;
        }
        return false;
    }
}
