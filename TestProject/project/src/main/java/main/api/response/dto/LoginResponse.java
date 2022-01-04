package main.api.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import main.api.response.dto.user.UserLoginResponse;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {

    boolean result;

    UserLoginResponse userLoginResponse;
}
