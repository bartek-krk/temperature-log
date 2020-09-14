package pl.bart.temperaturelog.services;

import pl.bart.temperaturelog.models.Measurement;

import java.util.List;

public interface MeasurementService {
    public List<Measurement> getByStationId(Long stationId);
}
