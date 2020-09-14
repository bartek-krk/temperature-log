package pl.bart.temperaturelog.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bart.temperaturelog.models.Measurement;
import pl.bart.temperaturelog.services.MeasurementService;

import java.util.List;

@RestController
@RequestMapping(value = "/measurements")
public class MeasurementRestController {

    @Autowired
    MeasurementService measurementService;

    @GetMapping(value = "/{stationId}")
    public List<Measurement> getMeasurementsByStationId(@PathVariable Long stationId) {
        return measurementService.getByStationId(stationId);
    }
}
