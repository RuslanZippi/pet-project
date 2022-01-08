package main.controller;

import main.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class DefaultController {

    @Autowired
    WeatherDataService weatherDataService;
    @GetMapping
    public String main(){

        return weatherDataService.getTemperatureInBd();
    }
}
