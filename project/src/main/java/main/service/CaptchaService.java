package main.service;

import com.github.cage.Cage;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import main.api.response.CaptchaResponse;
import main.model.Captcha;
import main.repository.CaptchaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Random;

import static java.time.LocalDateTime.*;

@Service
@AllArgsConstructor
public class CaptchaService {

    private final char[] WORDS = "qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM1234567890".toCharArray();
    private final ServiceTime serviceTime;

    @Autowired
    private CaptchaRep captchaRep;

    public CaptchaResponse getCaptchaResponse(){
        CaptchaResponse captchaResponse = new CaptchaResponse();

        captchaResponse.setSecret(getRandomSecretCode(WORDS));

        String code = getRandomText(WORDS);

        captchaResponse.setImage(getImage(code));

        saveCaptchaRep(captchaResponse, code);

        return captchaResponse;
    }

    private String getRandomText(char[] list){
        int size = 6;
        StringBuilder builder = new StringBuilder();

        for (int x = 0; x < size; x++){
            builder.append(list[new Random().nextInt(list.length)]);
        }

        return builder.toString();
    }

    private String getRandomSecretCode(char[] list){
        int size = 26;
        StringBuilder builder = new StringBuilder();

        for (int x = 0; x < size; x++){
            builder.append(list[new Random().nextInt(list.length)]);
        }

        return builder.toString();
    }

    private String getImage(String text){
        Cage cage = new Cage();

        return "data:image/png;base64, " + Base64.getEncoder().encodeToString(cage.draw(text));
    }

    private void saveCaptchaRep(CaptchaResponse captchaResponse, String code){
        Captcha captcha = new Captcha();
        captcha.setCode(code);
        captcha.setSecretCode(captchaResponse.getSecret());
        captcha.setTime(now());

        captchaRep.save(captcha);
    }

}
