package main.api.response.dto;

import lombok.Data;
import main.api.response.dto.user.CommentUser;

@Data
public class CommentToPostResponse {

    int id;

    long timestamp;

    String text;

    CommentUser user;
}
