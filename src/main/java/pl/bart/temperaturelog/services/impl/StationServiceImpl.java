package pl.bart.temperaturelog.services.impl;

import org.springframework.stereotype.Service;
import pl.bart.temperaturelog.models.Station;
import pl.bart.temperaturelog.repositories.StationRepository;
import pl.bart.temperaturelog.services.StationService;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public Station getStationById(Long id) {
        return stationRepository.findById(id).orElse(null);
    }
}
