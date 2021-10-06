package com.socialmediaplatform.api;


import com.socialmediaplatform.domain.post.Post;
import com.socialmediaplatform.domain.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @GetMapping
    public List<Post> fetchPostsList(){
        return postService.fetchPostsList();
    }

    @GetMapping("/{id}")
    public Post fetchPostById(@PathVariable("id") Long postId){
        return postService.fetchPostById(postId);
    }

    @PutMapping("/{id}")
    public Post updatePostById(@PathVariable("id") Long postId, @RequestBody Post post){
        return postService.updatePostById(postId,post);
    }

    @DeleteMapping("/{id}")
    public String deletePostById(@PathVariable("id") Long postId){
        postService.deletePostById(postId);
        return "Post has been successfully deleted";
    }

}
