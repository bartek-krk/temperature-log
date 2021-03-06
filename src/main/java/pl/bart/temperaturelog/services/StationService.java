package pl.bart.temperaturelog.services;

import pl.bart.temperaturelog.commands.StationForm;
import pl.bart.temperaturelog.models.Station;

import java.util.List;

public interface StationService {
    public List<Station> getAllStations ();
    public Station getStationById (Long id);
    public void saveAddedStation (StationForm stationForm);
    public void deleteById(Long id);
    public boolean isEmailFree(String email);
}
