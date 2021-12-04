package main.api.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @JsonProperty("e_mail")
    String mail;

    String password;

    String name;

    String captcha;

    @JsonProperty("captcha_secret")
    String captchaSecret;
}
