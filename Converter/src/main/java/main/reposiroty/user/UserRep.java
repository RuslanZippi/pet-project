package main.reposiroty.user;

import main.base.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<User,Long> {
    User findByUsername(String userName);
}
