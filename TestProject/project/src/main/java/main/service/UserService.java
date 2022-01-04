package main.service;

import main.api.request.user.UserRegisterRequest;
import main.model.User;
import main.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class UserService {

    @Autowired
    private UserRep userRep;

    public void saveUser(UserRegisterRequest userRegister){
        saveUserAfterRegistration(userRegister);
    }


    private void saveUserAfterRegistration(UserRegisterRequest userRegister){
        User user = new User();

        user.setName(userRegister.getName());
        user.setEmail(userRegister.getMail());
        user.setRegTime(LocalDateTime.now());
        user.setPassword(userRegister.getPassword());

        userRep.save(user);
    }

}
