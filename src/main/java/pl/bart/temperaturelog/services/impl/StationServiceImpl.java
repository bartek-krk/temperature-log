package pl.bart.temperaturelog.services.impl;

import org.springframework.stereotype.Service;
import pl.bart.temperaturelog.commands.StationForm;
import pl.bart.temperaturelog.converters.StationFormToStationConverter;
import pl.bart.temperaturelog.models.Station;
import pl.bart.temperaturelog.repositories.StationRepository;
import pl.bart.temperaturelog.services.StationService;
import pl.bart.temperaturelog.utilities.ApiKeyAuth;
import pl.bart.temperaturelog.utilities.EmailGenerator;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;
    private final StationFormToStationConverter stationFormToStationConverter;
    private final ApiKeyAuth apiKeyAuth;
    private final EmailGenerator emailGenerator;

    public StationServiceImpl(StationRepository stationRepository, StationFormToStationConverter stationFormToStationConverter, ApiKeyAuth apiKeyAuth, EmailGenerator emailGenerator) {
        this.stationRepository = stationRepository;
        this.stationFormToStationConverter = stationFormToStationConverter;
        this.apiKeyAuth = apiKeyAuth;
        this.emailGenerator = emailGenerator;
    }

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public Station getStationById(Long id) {
        return stationRepository.findById(id).orElse(null);
    }

    @Override
    public void saveAddedStation(StationForm stationForm) {
        Station station = stationFormToStationConverter.convert(stationForm);
        stationRepository.save(station);
        station = stationRepository.getByeMail(station.geteMail()).orElse(null);

        try {
            if (station != null) emailGenerator.sendCredentials(station);
        }
        catch (MessagingException e) {e.printStackTrace();}
    }

    @Override
    public boolean deleteByIdAndApiKey(Long id, String apiKey) {
        boolean isAuthenticated = apiKeyAuth.authenticate(id, apiKey);
        if (isAuthenticated) {
            stationRepository.deleteById(id);
            return true;
            }
        return false;
    }

    @Override
    public boolean isEmailFree(String email) {
        for(Station station : stationRepository.findAll()) {
            if (station.geteMail().equals(email)) return false;
        }
        return true;
    }
}
