package com.socialmediaplatform.api;


import com.socialmediaplatform.domain.publisher.Post;
import com.socialmediaplatform.domain.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PublisherService postService;

    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }


}
