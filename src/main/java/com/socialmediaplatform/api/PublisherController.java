package com.socialmediaplatform.api;


import com.socialmediaplatform.api.dto.PostDTO;
import com.socialmediaplatform.domain.publisher.Post;
import com.socialmediaplatform.domain.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/publisher")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService postService;

    @PostMapping("/posts")
    public Post createPost(@RequestBody PostDTO postDTO){
        return postService.createPost(postDTO);
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping
    public String getHello() {
        return postService.hello();
    }


}
