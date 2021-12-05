package main.service;

import lombok.RequiredArgsConstructor;
import main.api.response.RegistrationResponse;

import main.api.request.user.UserRegisterRequest;
import main.model.Captcha;
import main.model.User;
import main.repository.CaptchaRep;
import main.repository.UserRep;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRep userRep;
    private final CaptchaRep captchaRep;
    private final UserService userService;

    public RegistrationResponse getRegistrationResponse(UserRegisterRequest userRegister) {
        RegistrationResponse registrationResponse = new RegistrationResponse();

        Map<String, String> map = checkAllParam(userRegister);
        if (map.size()!= 0) {
            registrationResponse.setErrors(map);
        } else {
            registrationResponse.setResult(true);
            userService.saveUser(userRegister);
        }

        return registrationResponse;
    }

    private boolean checkMail(String mail) {
        User user = userRep.findByEmail(mail).orElseThrow(()-> new UsernameNotFoundException("user: " + mail + " not found"));
        return user == null;
    }

    private boolean checkPassword(String password) {
        return password.length() >= 6;
    }

    private boolean checkName(String name) {
        return !name.contains("[0-9]");
    }

    private boolean checkCaptcha(String captcha, String captchaSecret) {
        Captcha captcha1 = captchaRep.findBySecretCode(captchaSecret);

        if (captcha1 != null) {
            return captcha1.getCode().equals(captcha);
        }
        return false;
    }

    private Map<String, String> checkAllParam(UserRegisterRequest userRegister) {
        Map<String, String> map = new HashMap<>();

        if (!checkMail(userRegister.getMail())) {
            map.put("email", "Этот e-mail уже зарегестрирован!");
        }
        if (!checkPassword(userRegister.getPassword())) {
            map.put("password", "Пароль короче 6-ти символов");
        }
        if (!checkName(userRegister.getName())) {
            map.put("name", "Имя указано неверно");
        }
        if (!checkCaptcha(userRegister.getCaptcha(), userRegister.getCaptchaSecret())) {
            map.put("captcha", "Код с картинки указан не верно");
        }
        return map;
    }
}
