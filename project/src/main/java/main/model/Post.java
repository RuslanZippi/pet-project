package main.model;

import lombok.Data;
import main.api.response.dto.PostByLikeResponse;
import main.model.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "posts")
public class Post extends PostByLikeResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_active")
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(name = "moderator_status")
    private Status moderatorStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id")
    private User moderatorID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate time;

    private String title;

    private String text;

    @Column(name = "view_count")
    private int viewCount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tags_posts",
    joinColumns = @JoinColumn(name = "post_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tagList;

    @OneToMany(mappedBy = "post")
    private List<PostComment> postCommentList;

    @OneToMany(mappedBy = "post")
    private List<PostVotes> postVotesList;
}
