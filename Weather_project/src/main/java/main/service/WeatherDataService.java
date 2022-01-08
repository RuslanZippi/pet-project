package main.service;

import main.db.Weather;
import main.db.repos.WeatherRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;

@Service
public class WeatherDataService {
    @Autowired
    ParseWeather parseWeather;
    @Autowired
    WeatherRep weatherRep;

    public String getTemperatureInBd(){
        String temperature = "";
        Weather weather= weatherRep.findByWeatherDate(Date.valueOf(LocalDateTime.now().toLocalDate()));
        if (weather == null){
            weather = new Weather();
            try {
                temperature = parseWeather.getTemperature();
                weather.setWeatherValue(temperature);
                weather.setWeatherDate(Date.valueOf(LocalDateTime.now().toLocalDate()));
                weatherRep.save(weather);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            temperature = weather.getWeatherValue();
        }
        return temperature;
    }


}
