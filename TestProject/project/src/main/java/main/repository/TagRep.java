package main.repository;


import main.model.Tag;
import main.model.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRep extends CrudRepository<Tag,Long> {

    @Query("SELECT t FROM Tag t, TagToPost tp " +
            "WHERE t.id = tp.tag.id " +
            "AND tp.post.id = :id ")
    List<Tag> getTagByPostId(@Param("id") int id);
    List<Tag> findAll();
    List<Tag> findByName(String name);

    @Query("SELECT count(*) FROM Tag t, TagToPost tp, Post p " +
            "WHERE t.id = tp.tag.id " +
            "AND t.name = :name " +
            "AND p.moderatorStatus = :moderatorStatus " +
            "AND p.isActive = :isActive " +
            "AND tp.post.id = p.id")
    long countTagByName(@Param("name") String name, @Param("moderatorStatus") Status status, @Param("isActive") boolean isActive);


    @Query("SELECT count(*) FROM TagToPost tp, Post p " +
            "WHERE tp.post.id = p.id " +
            "AND p.moderatorStatus = :moderatorStatus " +
            "AND p.isActive = :isActive")
    long count(@Param("moderatorStatus") Status status, @Param("isActive") boolean isActive);

    @Query("SELECT t FROM Tag t, TagToPost tp, Post p " +
            "WHERE t.id = tp.tag.id " +
            "AND p.id = tp.post.id " +
            "AND p.moderatorStatus = :moderatorStatus " +
            "AND p.isActive = :isActive " +
            "GROUP by t.id " +
            "Order by count(*) desc")
    List<Tag> getMostPopularTag(@Param("moderatorStatus") Status status,@Param("isActive") boolean isActive, Pageable pageable);
}
