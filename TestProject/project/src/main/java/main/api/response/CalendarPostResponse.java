package main.api.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CalendarPostResponse {

    List<Integer> years;

    Map<String,Integer> posts;
}
