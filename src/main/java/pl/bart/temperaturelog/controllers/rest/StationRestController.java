package pl.bart.temperaturelog.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bart.temperaturelog.models.Station;
import pl.bart.temperaturelog.services.StationService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/stations")
public class StationRestController {

    @Autowired
    private StationService stationService;

    @GetMapping(value = "/")
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }

    @GetMapping(value = "/{stationId}")
    public Station getStationById(@PathVariable Long stationId) {
        return stationService.getStationById(stationId);
    }

    @DeleteMapping(value = "/{stationId}")
    public String deleteStation(@PathVariable Long stationId,
                              @RequestHeader("api_key") String apiKey) {
        boolean isSuccess = stationService.deleteByIdAndApiKey(stationId,apiKey);
        if (isSuccess) return "Delete successful";
        return "Error: item not deleted";
    }
}
