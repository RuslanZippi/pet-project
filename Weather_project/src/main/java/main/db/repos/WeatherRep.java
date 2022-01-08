package main.db.repos;

import main.db.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface WeatherRep extends CrudRepository<Weather,Long> {
    Weather findByWeatherDate(Date localDate);
}
