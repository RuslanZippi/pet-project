package main.api.response.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import main.model.User;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"isModerator", "regTime", "password", "code", "postList", "postVotesList", "postComments"})
public class UserLoginResponse extends User {

    int moderationCount;

    boolean moderation;

    boolean settings;
}
