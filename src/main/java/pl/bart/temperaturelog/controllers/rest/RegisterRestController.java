package pl.bart.temperaturelog.controllers.rest;

import org.springframework.web.bind.annotation.*;
import pl.bart.temperaturelog.commands.StationForm;
import pl.bart.temperaturelog.models.Station;
import pl.bart.temperaturelog.services.StationService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/register")
public class RegisterRestController {
    private final StationService stationService;

    public RegisterRestController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping
    public String registerStation(@RequestBody StationForm stationForm) {
        if (stationService.isEmailFree(stationForm.geteMail())) {
            stationService.saveAddedStation(stationForm);
            return "Station registered successfully";
        }
        else {
            return "Username occupied";
        }
    }
}
