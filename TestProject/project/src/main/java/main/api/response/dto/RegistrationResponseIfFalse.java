package main.api.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationResponseIfFalse {

    boolean result;

    Map<String,String> errors;
}
