package main.model;

import lombok.Data;
import main.model.enums.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @JoinColumn(name = "is_moderator")
    private boolean isModerator;

    @JoinColumn(name = "reg_time")
    private LocalDateTime regTime;

    private String name;

    private String email;

    private String password;

    private String code;

    private String photo;

    @OneToMany(mappedBy = "user")
    private List<Post> postList;

    @OneToMany(mappedBy = "user")
    private List<PostVotes> postVotesList;

    @OneToMany(mappedBy = "user")
    private List<PostComment> postComments;

    public Role getRole(){
        return isModerator ? Role.MODERATOR : Role.USER;
    }
}
