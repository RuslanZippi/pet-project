package main.api.response;


import lombok.Data;
import main.api.response.dto.RegistrationResponseIfFalse;

@Data
public class RegistrationResponse extends RegistrationResponseIfFalse {

    boolean result;

}
