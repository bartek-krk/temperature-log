package pl.bart.temperaturelog.services.impl;

import org.springframework.stereotype.Service;
import pl.bart.temperaturelog.models.Measurement;
import pl.bart.temperaturelog.repositories.MeasurementRepository;
import pl.bart.temperaturelog.services.MeasurementService;

import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {
    private final MeasurementRepository measurementRepository;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Override
    public List<Measurement> getByStationId(Long stationId) {
        return measurementRepository.getAllByStationId(stationId);
    }
}
