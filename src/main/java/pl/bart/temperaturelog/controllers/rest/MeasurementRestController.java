package pl.bart.temperaturelog.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bart.temperaturelog.commands.MeasurementDTO;
import pl.bart.temperaturelog.models.Measurement;
import pl.bart.temperaturelog.services.MeasurementService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/measurements")
public class MeasurementRestController {

    @Autowired
    MeasurementService measurementService;

    @GetMapping(value = "/{stationId}")
    public List<Measurement> getMeasurementsByStationId(@PathVariable Long stationId) {
        return measurementService.getByStationId(stationId);
    }

    @PutMapping(value = "/")
    public String saveMeasurement(@RequestHeader(name = "api_key") String apiKey,
                                @RequestBody MeasurementDTO measurementDTO) {
        boolean isSuccess = measurementService.saveAddedMeasurement(measurementDTO, apiKey);
        if (isSuccess) return "Success";
        return "Failed";
    }
}
