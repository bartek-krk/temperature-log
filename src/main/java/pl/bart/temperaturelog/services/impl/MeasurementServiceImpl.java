package pl.bart.temperaturelog.services.impl;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;
import pl.bart.temperaturelog.commands.MeasurementDTO;
import pl.bart.temperaturelog.converters.MeasurementDTOToMeasurementConverter;
import pl.bart.temperaturelog.exceptions.StationNotExistingException;
import pl.bart.temperaturelog.models.Measurement;
import pl.bart.temperaturelog.models.Station;
import pl.bart.temperaturelog.repositories.MeasurementRepository;
import pl.bart.temperaturelog.repositories.StationRepository;
import pl.bart.temperaturelog.services.MeasurementService;
import pl.bart.temperaturelog.services.StationService;
import pl.bart.temperaturelog.utilities.ApiCallNumberValidator;
import pl.bart.temperaturelog.utilities.ApiKeyAuth;
import pl.bart.temperaturelog.utilities.EmailGenerator;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {
    private final StationRepository stationRepository;
    private final MeasurementRepository measurementRepository;
    private final MeasurementDTOToMeasurementConverter measurementDTOToMeasurementConverter;
    private final ApiKeyAuth apiKeyAuth;
    private final ApiCallNumberValidator apiCallNumberValidator;
    private final EmailGenerator emailGenerator;

    public MeasurementServiceImpl(StationRepository stationRepository, MeasurementRepository measurementRepository, MeasurementDTOToMeasurementConverter measurementDTOToMeasurementConverter, ApiKeyAuth apiKeyAuth, ApiCallNumberValidator apiCallNumberValidator, EmailGenerator emailGenerator) {
        this.stationRepository = stationRepository;
        this.measurementRepository = measurementRepository;
        this.measurementDTOToMeasurementConverter = measurementDTOToMeasurementConverter;
        this.apiKeyAuth = apiKeyAuth;
        this.apiCallNumberValidator = apiCallNumberValidator;
        this.emailGenerator = emailGenerator;
    }

    @Override
    public List<Measurement> getByStationId(Long stationId) {
        if(!measurementRepository.existsByStationId(stationId)) throw new StationNotExistingException();
        return measurementRepository.getAllByStationId(stationId);
    }

    @Override
    public boolean saveAddedMeasurement(MeasurementDTO measurementDTO) {
        Station station = stationRepository.findById(measurementDTO.getStationId()).orElse(null);
        boolean isWithinCapacity = apiCallNumberValidator.validateNumberOfMeasurements(measurementDTO.getStationId());
        if (isWithinCapacity) {
            station.setNumberOfMeasurements(station.getNumberOfMeasurements()+1);
            stationRepository.save(station);
            measurementRepository.save(measurementDTOToMeasurementConverter.convert(measurementDTO));
            return true;
        }
        else{
            try {
                emailGenerator.sendMaxApiCallsWarning(station);
            }
            catch (MessagingException e) { e.printStackTrace(); }
        }
        return false;
    }
}
