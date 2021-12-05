package main.repository;


import main.model.Post;
import main.model.enums.Status;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.MapKey;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public interface PostRep extends CrudRepository<Post, Long> {


    Post findById(int id);


    List<Post> findPostByModeratorStatusAndIsActive(Status moderator_status, boolean isActive, Pageable pageable);

    @Query("FROM Post p WHERE p.moderatorStatus = :moderatorStatus " +
            "AND p.isActive = :isActive GROUP BY p.id ORDER BY p.postCommentList.size DESC")
    List<Post> getPostsOrderByCommentCount(@Param("moderatorStatus") Status status, @Param("isActive") boolean isActive, Pageable pageable);

    @Query("SELECT p, " +
            "   (SELECT SUM(pv.value) AS countLikes FROM PostVotes pv WHERE pv.post.id = p.id) " +
            "   AS countLikes " +
            "FROM Post p LEFT JOIN PostVotes pv " +
            "ON pv.post.id = p.id " +
            "WHERE " +
            "   p.isActive = :isActive AND " +
            "   p.moderatorStatus = :moderatorStatus AND " +
            "   p.time <= now() " +
            "GROUP BY p.id")
    List<Post> getPostOrderByLike(@Param("moderatorStatus") Status status, @Param("isActive") boolean isActive, Pageable pageable);

    @Query("From Post p WHERE p.moderatorStatus = :moderatorStatus " +
            "AND p.isActive = :isActive AND p.title like %:title%")
    List<Post> getPostBySearch(@Param("moderatorStatus") Status status, @Param("title") String title, @Param("isActive") boolean isActive, Pageable pageable);

    @Query("SELECT p FROM TagToPost tp, Tag t, Post p " +
            "WHERE t.name = :tag " +
            "AND t.id = tp.tag.id " +
            "AND tp.post.id = p.id " +
            "AND p.moderatorStatus = :moderatorStatus " +
            "AND p.isActive = :isActive " +
            "GROUP BY p.id")
    List<Post> getPostByTags(@Param("moderatorStatus") Status status, @Param("tag") String tag, @Param("isActive") boolean isActive, Pageable pageable);


    @Query("SELECT count(*) FROM Post p, PostComment pc " +
            "WHERE p.isActive = :isActive " +
            "AND p.moderatorStatus = :moderatorStatus " +
            "AND p.id = pc.post.id " +
            "AND p.id = :id")
    long getCommentsCount(@Param("isActive") boolean isActive, @Param("moderatorStatus") Status status, @Param("id") int id);

    @Query("SELECT count(*) FROM Post p, PostVotes pv " +
            "WHERE pv.post.id = p.id " +
            "AND pv.value = 1 " +
            "AND p.id = :id")
    long getLikeCount(@Param("id") int id);

    @Query("SELECT count(*) FROM Post p, PostVotes pv " +
            "WHERE pv.post.id = p.id " +
            "AND pv.value = -1 " +
            "AND p.id = :id")
    long getDislikeCount(@Param("id") int id);


    @Query("SELECT p.time, count(*) FROM Post p " +
            "WHERE p.moderatorStatus = :moderatorStatus " +
            "AND p.isActive = :isActive " +
            "group by p.time")
    List<Object[]> getCountPostByTime(@Param("moderatorStatus") Status status, @Param("isActive") boolean isActive);

    @Query("SELECT p FROM Post p " +
            "WHERE p.time = :time " +
            "AND p.moderatorStatus = :moderatorStatus " +
            "AND p.isActive = :isActive ")
    List<Post> getPostByDate(@Param("moderatorStatus") Status status, @Param("isActive") boolean isActive, @Param("time")LocalDate date);
}
