package main.repository;


import main.model.Post;
import main.model.PostComment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRep extends CrudRepository<PostComment, Long> {


    @Query("SELECT postComment AS count " +
            "FROM PostComment postComment " +
            "WHERE postComment.post.id  = :postId ")
    List<PostComment> getCommentList(@Param("postId") int postId);
}
