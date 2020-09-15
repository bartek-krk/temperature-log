package pl.bart.temperaturelog.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bart.temperaturelog.commands.StationForm;
import pl.bart.temperaturelog.services.StationService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final StationService stationService;

    public RegisterController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public String getRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new StationForm());
        return "register/register_form";
    }

    @PostMapping
    public String saveStation(StationForm stationForm, BindingResult bindingResult) {
        stationService.saveAddedStation(stationForm);
        return "register/success";
    }
}
