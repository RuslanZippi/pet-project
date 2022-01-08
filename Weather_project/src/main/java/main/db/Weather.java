package main.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "weather_history")
public class Weather {

    @JoinColumn(name = "weather_date")
    @Id
    private Date weatherDate;

    @JoinColumn(name = "weather_value")
    private String weatherValue;
}
