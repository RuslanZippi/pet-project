package main.repository;


import main.model.PostVotes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostVotesRep extends CrudRepository<PostVotes, Long> {

}
