package main.service;

import lombok.AllArgsConstructor;
import main.api.request.user.UserLoginRequest;
import main.api.response.dto.LoginResponse;
import main.api.response.dto.user.UserLoginResponse;
import main.model.User;
import main.repository.UserRep;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {



    private final AuthenticationManager authenticationManager;
    private final UserRep userRep;

    public LoginResponse getLoginResponse(UserLoginRequest userLoginRequest) {
        LoginResponse loginResponse = new LoginResponse();

        if (addUserOnSecurityContext(userLoginRequest).getId() !=0){
            loginResponse.setResult(true);
            loginResponse.setUserLoginResponse(addUserOnSecurityContext(userLoginRequest));
        }
        else {
            loginResponse.setResult(false);
        }

        return loginResponse;
    }

    private UserLoginResponse addUserOnSecurityContext(UserLoginRequest userLoginRequest) {
        System.out.println(userLoginRequest.getPassword());
        System.out.println(userLoginRequest.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return packingUserLoginResponse(user);
    }

    private UserLoginResponse packingUserLoginResponse(org.springframework.security.core.userdetails.User user1) {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        User user = userRep.findByEmail(user1.getPassword()).orElseThrow(() -> new UsernameNotFoundException(user1.getUsername()));

        if (user != null) {
            userLoginResponse.setId(user.getId());
            userLoginResponse.setModerationCount(0);
            userLoginResponse.setModeration(user.isModerator());
            userLoginResponse.setEmail(user.getEmail());
            userLoginResponse.setPhoto(user.getPhoto());
            userLoginResponse.setName(user.getName());
            userLoginResponse.setSettings(true);
        }


        return userLoginResponse;
    }
}
