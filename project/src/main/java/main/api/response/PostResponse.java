package main.api.response;

import lombok.Data;
import main.api.response.dto.PostValueResponse;

import java.util.List;

@Data
public class PostResponse {

    private int count;

    private List<PostValueResponse> posts;

    public PostResponse(List<PostValueResponse> posts) {
        this.posts = posts;
        count = posts.size();
    }
}
