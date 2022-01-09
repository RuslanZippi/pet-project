package main.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ParseWeather {
    private String temperature;
    private Logger logger = Logger.getLogger(ParseWeather.class.getName());

    public String getTemperature() throws IOException {
        URL url = new URL("https://yandex.ru");
        URLConnection connection = url.openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        scanner.useDelimiter("\\Z");
        Pattern p = Pattern.compile("class='weather__temp'>[\\D0-9]+");
        Matcher matcher = p.matcher(scanner.next());
        if (matcher.find()) {
            temperature = matcher.group().split("[>]")[1];

            logger.info(matcher.group());
            logger.info(temperature);

        } else {
            temperature = "Not found temperature";

            logger.info(temperature);
        }
        return temperature;
    }
}
