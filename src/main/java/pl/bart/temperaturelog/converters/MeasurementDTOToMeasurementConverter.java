package pl.bart.temperaturelog.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.bart.temperaturelog.commands.MeasurementDTO;
import pl.bart.temperaturelog.models.Measurement;
import pl.bart.temperaturelog.models.Station;
import pl.bart.temperaturelog.services.StationService;

import java.time.Instant;

@Component
public class MeasurementDTOToMeasurementConverter implements Converter<MeasurementDTO, Measurement> {
    private final StationService stationService;

    public MeasurementDTOToMeasurementConverter(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public Measurement convert(MeasurementDTO measurementDTO) {
        Measurement returnMeasurement = new Measurement();
        Station station = stationService.getStationById(measurementDTO.getCredentials().getId());
        returnMeasurement.setTimestamp(Instant.now().getEpochSecond());
        returnMeasurement.setTemperature(measurementDTO.getTemperature());
        returnMeasurement.setStation(station);
        return returnMeasurement;
    }
}
