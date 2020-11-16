package pl.bart.temperaturelog.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bart.temperaturelog.aop.annotations.RestrictedAccess;
import pl.bart.temperaturelog.commands.MeasurementDTO;
import pl.bart.temperaturelog.models.Measurement;
import pl.bart.temperaturelog.services.MeasurementService;

import java.util.List;
import java.util.Map;

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

    @RestrictedAccess
    @PutMapping(value = "/")
    public void saveMeasurement(@RequestBody Map<String,Object> payload) {
        ObjectMapper om = new ObjectMapper();
        MeasurementDTO measurementDTO = om.convertValue(payload.get("measurement"),MeasurementDTO.class);
        measurementService.saveAddedMeasurement(measurementDTO);
    }
}
