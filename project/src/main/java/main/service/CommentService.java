package main.service;

import lombok.RequiredArgsConstructor;
import main.api.response.dto.CommentToPostResponse;
import main.api.response.dto.user.CommentUser;
import main.model.Post;
import main.model.PostComment;
import main.repository.PostCommentRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    @Autowired
    PostCommentRep postCommentRep;

    private final ServiceTime serviceTime;

    public List<CommentToPostResponse> getCommentListToPostResponse(Post post){
        List<CommentToPostResponse> commentToPostResponses = new ArrayList<>();

        List<PostComment> list = getPostCommentByPostId(post);
        for (PostComment postComment : list){
            commentToPostResponses.add(packingComment(postComment));
        }

        return commentToPostResponses;
    }

    private List<PostComment> getPostCommentByPostId(Post post){
        return postCommentRep.getCommentList(post.getId());
    }

    private CommentToPostResponse packingComment(PostComment postComment){
        CommentToPostResponse commentToPostResponse = new CommentToPostResponse();

        CommentUser commentUser = new CommentUser();

        commentUser.setId(postComment.getUser().getId());
        commentUser.setName(postComment.getUser().getName());
        commentUser.setPhoto(postComment.getUser().getPhoto());

        commentToPostResponse.setId(postComment.getId());
        commentToPostResponse.setTimestamp(serviceTime.getTimeInSec(postComment.getTime()));
        commentToPostResponse.setText(postComment.getText());
        commentToPostResponse.setUser(commentUser);

        return commentToPostResponse;
    }
}
