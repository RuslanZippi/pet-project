package main.controller;

import main.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@Controller
@RequestMapping("/weather")
public class DefaultController {
    private Logger logger = Logger.getLogger(DefaultController.class.getName());

    @Autowired
    WeatherDataService weatherDataService;
    @GetMapping
    public String main(Model model){
        model.addAttribute("temperature",weatherDataService.getTemperatureInBd());
        model.addAttribute("date", weatherDataService.getDate());
        logger.info(model.toString());

        return "index";
    }
}
