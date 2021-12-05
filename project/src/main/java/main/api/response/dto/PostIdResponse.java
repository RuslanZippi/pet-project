package main.api.response.dto;

import lombok.Data;
import main.api.response.dto.user.User;

import java.util.List;

@Data
public class PostIdResponse {

    int id;

    long timestamp;

    boolean active;

    User user;

    String title;

    String text;

    int likeCount;

    int dislikeCount;

    int viewCount;

    List<CommentToPostResponse> comments;

    List<String> tags;

}
