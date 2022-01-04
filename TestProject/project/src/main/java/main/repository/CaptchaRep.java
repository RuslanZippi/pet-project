package main.repository;


import main.model.Captcha;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptchaRep extends CrudRepository<Captcha, Long> {

    Captcha findBySecretCode(String secretCode);
}
