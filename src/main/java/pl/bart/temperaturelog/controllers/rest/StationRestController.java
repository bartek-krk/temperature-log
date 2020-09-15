package pl.bart.temperaturelog.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bart.temperaturelog.models.Station;
import pl.bart.temperaturelog.services.StationService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/stations")
public class StationRestController {

    @Autowired
    private StationService stationService;

    @GetMapping(value = "/all")
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }

    @GetMapping(value = "/{stationId}")
    public Station getStationById(@PathVariable Long stationId) {
        return stationService.getStationById(stationId);
    }
}
