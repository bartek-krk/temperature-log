package pl.bart.temperaturelog.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bart.temperaturelog.aop.annotations.RestrictedAccess;
import pl.bart.temperaturelog.models.Station;
import pl.bart.temperaturelog.security.Credentials;
import pl.bart.temperaturelog.services.StationService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @RestrictedAccess
    @DeleteMapping(value = "/")
    public void deleteStation(@RequestBody Map<String, Object> payload) {
        ObjectMapper om = new ObjectMapper();
        Credentials credentials = om.convertValue(payload.get("credentials"),Credentials.class);
        stationService.deleteById(credentials.getId());
    }
}
