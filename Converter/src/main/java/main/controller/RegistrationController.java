package main.controller;

import main.base.user.Role;
import main.base.user.User;
import main.reposiroty.user.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRep userRep;


    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam(name = "username") String userName, @RequestParam(name = "userpassword") String userPassword , Model model){
        User userFromDb =userRep.findByUsername(userName);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(userFromDb != null){
            model.addAttribute("massage", "Пользователь существует");
            return "/registration";
        }
        User user = new User();
        user.setActive(true);
        user.setUsername(userName);
        user.setPassword(userPassword);
        user.setHashPassword(passwordEncoder.encode(userPassword));
        user.setRoles(Collections.singleton(Role.USER));
        userRep.save(user);
        return "redirect:/";
    }
}
