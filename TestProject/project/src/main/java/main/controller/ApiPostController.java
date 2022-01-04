package main.controller;

import main.api.response.PostResponse;
import main.api.response.dto.PostIdResponse;
import main.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;

@RestController
@RequestMapping("api/post")
public class ApiPostController {

    @Autowired
    private PostService postService;

    @GetMapping
    private PostResponse post(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "recent") String mode) {
        return postService.getPostResponse(offset, limit, mode);
    }

    @GetMapping("/search")
    private ResponseEntity<PostResponse> postSearch(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit, @RequestParam String query){
        return new ResponseEntity<PostResponse>(postService.getPostResponseSearch(offset,limit,query), HttpStatus.OK);
    }

    private String apiPost() {
        return "index";
    }


    @GetMapping("/byTag")
    private PostResponse getPostByTag(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit, @RequestParam String tag){
        return postService.getPostResponseByTags(offset,limit,tag);
    }

    @GetMapping("/byDate")
    private ResponseEntity<PostResponse> getPostByDate(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit, @RequestParam String date){
        return new ResponseEntity<PostResponse>(postService.getPostByDate(offset,limit,date), HttpStatus.OK);
    }


    @GetMapping("/{ID}")
    private PostIdResponse getPostById(@PathVariable int ID){
        return postService.getPostListById(ID);
    }
}
