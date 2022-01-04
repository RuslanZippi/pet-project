package main.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class ServiceTime {

    public LocalDateTime getLocalDateTime(LocalDate localDate){
        return localDate.atStartOfDay();
    }

    public ZonedDateTime getZonedDateTime(LocalDateTime localDateTime){
        return localDateTime.atZone(ZoneId.of("Europe/Moscow"));
    }

    public long getTimeInSec(LocalDate localDate){
        return getZonedDateTime(getLocalDateTime(localDate)).toInstant().toEpochMilli()/1000;
    }
}
