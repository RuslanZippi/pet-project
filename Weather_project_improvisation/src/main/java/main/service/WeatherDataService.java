package main.service;

import main.db.Weather;
import main.db.repos.WeatherRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class WeatherDataService {
    private final Logger logger = Logger.getLogger(WeatherDataService.class.getName());

    @Autowired
    ParseWeather parseWeather;
    @Autowired
    WeatherRep weatherRep;

    public String getTemperatureInBd() {
        String temperature = "";
        Weather weather = weatherRep.findByWeatherDate(Date.valueOf(LocalDateTime.now().toLocalDate()));

        logger.info(weather.toString());

        if (weather == null) {
            weather = new Weather();
            try {
                temperature = parseWeather.getTemperature();
                weather.setWeatherValue(temperature);
                weather.setWeatherDate(Date.valueOf(LocalDateTime.now().toLocalDate()));

                logger.info(temperature);
                logger.info(Date.valueOf(LocalDateTime.now().toLocalDate()).toString());

                weatherRep.save(weather);
            } catch (IOException e) {

                logger.warning(e.toString());

            }
        } else {
            temperature = weather.getWeatherValue();

            logger.info(temperature);

        }
        return temperature;
    }
    public String getDate(){
        Weather weather = weatherRep.findByWeatherDate(Date.valueOf(LocalDateTime.now().toLocalDate()));
        return weather.getWeatherDate().toString();
    }


}
