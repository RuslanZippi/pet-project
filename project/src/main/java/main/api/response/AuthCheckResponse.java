package main.api.response;

import lombok.Data;
import main.model.User;
import org.springframework.stereotype.Component;

@Data
@Component
public class AuthCheckResponse {
    private boolean result;

    private User user;

}
