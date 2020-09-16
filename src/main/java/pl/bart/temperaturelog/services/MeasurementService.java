package pl.bart.temperaturelog.services;

import pl.bart.temperaturelog.commands.MeasurementDTO;
import pl.bart.temperaturelog.models.Measurement;

import java.util.List;

public interface MeasurementService {
    public List<Measurement> getByStationId(Long stationId);
    public boolean saveAddedMeasurement(MeasurementDTO measurementDTO, String apiKey);
}
