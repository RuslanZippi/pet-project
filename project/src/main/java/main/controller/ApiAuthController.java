package main.controller;

import lombok.AllArgsConstructor;
import main.api.request.user.UserLoginRequest;
import main.api.response.AuthCheckResponse;
import main.api.response.CaptchaResponse;
import main.api.response.RegistrationResponse;
import main.api.response.dto.LoginResponse;
import main.api.request.user.UserRegisterRequest;
import main.repository.UserRep;
import main.service.AuthCheckService;
import main.service.CaptchaService;
import main.service.LoginService;
import main.service.RegistrationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class ApiAuthController {



    private final AuthCheckService authCheckService;
    private final CaptchaService captchaService;
    private final RegistrationService registrationService;
    private final LoginService loginService;


    @GetMapping("/check")
    private AuthCheckResponse check() {
        return authCheckService.getAuthCheck();
    }


    @GetMapping("/captcha")
    private CaptchaResponse captcha() {
        return captchaService.getCaptchaResponse();
    }

    @PostMapping("/register")
    private RegistrationResponse registration(@RequestBody UserRegisterRequest userRegister) {
        return registrationService.getRegistrationResponse(userRegister);
    }

    @PostMapping("/login")
    private LoginResponse login(@RequestBody UserLoginRequest userLoginRequest){
        return loginService.getLoginResponse(userLoginRequest);
    }



}
