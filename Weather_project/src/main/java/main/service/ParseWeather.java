package main.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ParseWeather {
    private  String temperature;
     public String getTemperature() throws IOException {
        URL url = new URL("https://yandex.ru");
        URLConnection connection = url.openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        scanner.useDelimiter("\\Z");
        Pattern p = Pattern.compile("class='weather__temp'>.[0-9]+|[0-9]");
        Matcher matcher = p.matcher(scanner.next());
        if (matcher.find()){
            temperature = matcher.group().split("[>]")[1];
        }
        else {
            temperature = "Not found temperature";
        }
        return temperature;
    }
}
