package main.service;

import lombok.RequiredArgsConstructor;
import main.api.response.PostResponse;
import main.api.response.CalendarPostResponse;
import main.api.response.dto.PostIdResponse;
import main.api.response.dto.PostValueResponse;
import main.api.response.dto.TagToResponse;
import main.api.response.dto.user.User;
import main.model.Post;
import main.model.enums.Status;
import main.repository.PostCommentRep;
import main.repository.PostRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ServiceTime serviceTime;
    private final CommentService commentService;
    private final TagService tagService;


    @Autowired
    private PostRep postRep;
    @Autowired
    private PostCommentRep postCommentRep;

    public PostResponse getPostResponse(int offset, int limit, String mode) {
        List<PostValueResponse> list = new ArrayList<>();
        switch (mode) {
            case "popular": //+
                list = getPostListPopular(offset, limit);
                break;
            case "recent": //+
                list = getPostList(offset, limit);
                break;
            case "best":
                list = getPostListBest(offset, limit);
                break;
            case "early": //+
                list = getPostListEarly(offset, limit);
                break;
        }
        PostResponse postResponse = new PostResponse(list);
        return postResponse;
    }

    public PostResponse getPostResponseSearch(int offset, int limit, String query) {
        List<PostValueResponse> list;

        list = getPostListBySearch(offset, limit, query);
        PostResponse postResponse = new PostResponse(list);
        return postResponse;
    }

    public PostResponse getPostResponseByTags(int offset, int limit, String tag) {
        List<PostValueResponse> list;

        list = getPostListByTag(offset, limit, tag);

        PostResponse postResponse = new PostResponse(list);
        return postResponse;
    }

    public CalendarPostResponse getPostByCalendar() {
        CalendarPostResponse calendarPost = new CalendarPostResponse();

        calendarPost.setPosts(getPostByTime());
        calendarPost.setYears(getSetYears());

        return calendarPost;
    }

    public PostResponse getPostByDate(int offset, int limit, String date) {

        List<PostValueResponse> list = getPostListByDate(offset, limit, date);

        PostResponse postResponse = new PostResponse(list);
        return postResponse;
    }

    public PostIdResponse getPostListById(int id) {

        Post post = postRep.findById(id);

        PostIdResponse postIdResponses = packingPostById(post);

        return postIdResponses;
    }

    private List<PostValueResponse> getPostListByTag(int offset, int limit, String tag) {
        List<PostValueResponse> postValueResponses = new ArrayList<>();
        List<Post> list = postRep.getPostByTags(Status.ACCEPTED, tag, true, PageRequest.of(offset, limit));

        for (Post p : list) {
            postValueResponses.add(packingPostValue(p));
        }
        return postValueResponses;
    }

    private List<PostValueResponse> getPostList(int offset, int limit) {
        List<PostValueResponse> postValueResponseList = new ArrayList<>();
        List<Post> list = postRep.findPostByModeratorStatusAndIsActive(Status.ACCEPTED, true, PageRequest.of(offset, limit, Sort.by("time").descending()));

        for (Post p : list) {
            postValueResponseList.add(packingPostValue(p));
        }
        return postValueResponseList;
    }

    private List<PostValueResponse> getPostListBySearch(int offset, int limit, String query) {
        List<PostValueResponse> postValueResponseList = new ArrayList<>();
        List<Post> list = postRep.getPostBySearch(Status.ACCEPTED, query, true, PageRequest.of(offset, limit));


        for (Post p : list) {
            postValueResponseList.add(packingPostValue(p));
        }

        return postValueResponseList;
    }


    private List<PostValueResponse> getPostListEarly(int offset, int limit) {
        List<PostValueResponse> postValueResponseList = new ArrayList<>();
        List<Post> list = postRep.findPostByModeratorStatusAndIsActive(Status.ACCEPTED, true, PageRequest.of(offset, limit, Sort.by("time")));

        for (Post p : list) {
            postValueResponseList.add(packingPostValue(p));
        }

        return postValueResponseList;
    }


    private List<PostValueResponse> getPostListPopular(int offset, int limit) {
        List<PostValueResponse> postValueResponseList = new ArrayList<>();
        List<Post> list = postRep.getPostsOrderByCommentCount(Status.ACCEPTED, true, PageRequest.of(offset, limit));

        for (Post p : list) {
            postValueResponseList.add(packingPostValue(p));
        }

        return postValueResponseList;
    }

    private Map<String, Integer> getPostByTime() {
        Map<String, Integer> mapPost = new HashMap<>();

        List<Object[]> list = postRep.getCountPostByTime(Status.ACCEPTED, true);

        for (Object[] o : list) {
            mapPost.put(o[0].toString(), Integer.parseInt(o[1].toString()));
        }

        return mapPost;
    }

    private List<Integer> getSetYears() {
        HashSet<Integer> set = new HashSet<>();
        List<Object[]> list = postRep.getCountPostByTime(Status.ACCEPTED, true);

        for (Object[] o : list) {
            set.add(Integer.parseInt(o[0].toString().split("-")[0]));
        }
        return new ArrayList<>(set);
    }


    private List<PostValueResponse> getPostListBest(int offset, int limit) {
        List<PostValueResponse> postValueResponseList = new ArrayList<>();
        List<Post> list = postRep.getPostOrderByLike(Status.ACCEPTED, true, PageRequest.of(offset, limit, Sort.by("countLikes").descending()));

        for (Post p : list) {
            postValueResponseList.add(packingPostValue(p));

        }
        return postValueResponseList;
    }

    private List<PostValueResponse> getPostListByDate(int offset, int limit, String date) {
        List<PostValueResponse> postValueResponses = new ArrayList<>();

        List<Post> list = postRep.getPostByDate(Status.ACCEPTED, true, LocalDate.parse(date));


        for (Post p : list) {
            postValueResponses.add(packingPostValue(p));
        }

        return postValueResponses;
    }

    // метод пакует посты, полученные при запросе, в нужный формат для вывода на фронт
    private PostValueResponse packingPostValue(Post post) {
        User user = new User();

        user.setId(post.getUser().getId());
        user.setName(post.getUser().getName());
        PostValueResponse postValueResponse = new PostValueResponse();

        postValueResponse.setId(post.getId());

        postValueResponse.setAnnounce(post.getText());

        postValueResponse.setTitle(post.getTitle());

        postValueResponse.setUser(user);

        postValueResponse.setCommentCount((int) postRep.getCommentsCount(true, Status.ACCEPTED, post.getId()));
        postValueResponse.setLikeCount((int) postRep.getLikeCount(post.getId()));
        postValueResponse.setDislikeCount((int) postRep.getDislikeCount(post.getId()));
        postValueResponse.setViewCount(post.getViewCount());


        postValueResponse.setTimestamp(serviceTime.getTimeInSec(post.getTime()));

        return postValueResponse;
    }

    private PostIdResponse packingPostById(Post post) {
        PostIdResponse postIdResponse = new PostIdResponse();

        User user = new User();

        user.setId(post.getUser().getId());
        user.setName(post.getUser().getName());

        postIdResponse.setId(post.getId());
        postIdResponse.setTimestamp(serviceTime.getTimeInSec(post.getTime()));
        postIdResponse.setActive(post.isActive());

        postIdResponse.setUser(user);

        postIdResponse.setTitle(post.getTitle());
        postIdResponse.setText(post.getText());
        postIdResponse.setLikeCount((int) postRep.getLikeCount(post.getId()));
        postIdResponse.setDislikeCount((int) postRep.getDislikeCount(post.getId()));
        postIdResponse.setViewCount(post.getViewCount());
        postIdResponse.setComments(commentService.getCommentListToPostResponse(post));
        ArrayList<String> listTags = new ArrayList<>();
        List<TagToResponse> tagList = tagService.getTagsByPostId(post.getId()).getTags();

        if (tagList.size() != 0) {
            for (TagToResponse tr : tagList) {
                listTags.add(tr.getName());
            }
        }
        postIdResponse.setTags(listTags);

        return postIdResponse;
    }


}
