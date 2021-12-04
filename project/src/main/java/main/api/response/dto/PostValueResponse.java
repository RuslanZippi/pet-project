package main.api.response.dto;

import lombok.Data;
import main.api.response.dto.user.User;

@Data
public class PostValueResponse {

    private int id;

    private long timestamp;

    private User user;

    private String title;

    private String announce;

    private int likeCount;

    private int dislikeCount;

    private int commentCount;

    private int viewCount;

}
