package pl.bart.temperaturelog.services.impl;

import org.springframework.stereotype.Service;
import pl.bart.temperaturelog.commands.MeasurementDTO;
import pl.bart.temperaturelog.converters.MeasurementDTOToMeasurementConverter;
import pl.bart.temperaturelog.models.Measurement;
import pl.bart.temperaturelog.repositories.MeasurementRepository;
import pl.bart.temperaturelog.services.MeasurementService;
import pl.bart.temperaturelog.utilities.ApiKeyAuth;

import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final MeasurementDTOToMeasurementConverter measurementDTOToMeasurementConverter;
    private final ApiKeyAuth apiKeyAuth;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository, MeasurementDTOToMeasurementConverter measurementDTOToMeasurementConverter, ApiKeyAuth apiKeyAuth) {
        this.measurementRepository = measurementRepository;
        this.measurementDTOToMeasurementConverter = measurementDTOToMeasurementConverter;
        this.apiKeyAuth = apiKeyAuth;
    }

    @Override
    public List<Measurement> getByStationId(Long stationId) {
        return measurementRepository.getAllByStationId(stationId);
    }

    @Override
    public boolean saveAddedMeasurement(MeasurementDTO measurementDTO, String apiKey) {
        boolean isAuthenticated = apiKeyAuth.authenticate(measurementDTO.getStationId(), apiKey);
        if (isAuthenticated) {
            measurementRepository.save(measurementDTOToMeasurementConverter.convert(measurementDTO));
            return true;
        }
        return false;
    }
}
