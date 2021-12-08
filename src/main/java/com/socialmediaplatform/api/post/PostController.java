package com.socialmediaplatform.api.post;

import com.socialmediaplatform.domain.post.PostService;
import com.socialmediaplatform.domain.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    @Operation(summary = "user details", security = @SecurityRequirement(name = "bearer"))
    public List<PostDTO> getMyFeed(){
        return postService.getMyFeed().stream().map(PostDTO::fromDomain).collect(Collectors.toUnmodifiableList());
    }

    @PostMapping
    @Operation(summary = "user details", security = @SecurityRequirement(name = "bearer"))
    public PostDTO create(@RequestBody @Valid CreatePostDTO createPostDTO) {
        return PostDTO.fromDomain(postService.createPost(createPostDTO.toCommand()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "user details", security = @SecurityRequirement(name = "bearer"))
    public PostDTO update(@PathVariable Long id, String content){
        return PostDTO.fromDomain(postService.updatePost(id,content));
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "user details", security = @SecurityRequirement(name = "bearer"))
    public void comment(@PathVariable Long id, String content){
        postService.comment(new PostService.Command.Comment() {
            @Override
            public Long getPostId() {
                return id;
            }
            @Override
            public String getContent() {
                return content;
            }
        });

    }

    @PutMapping("/{id}/comments")
    @Operation(summary = "user details", security = @SecurityRequirement(name = "bearer"))
    public void updateComment(@PathVariable Long id, String content){
        //TODO2

    }

    @DeleteMapping("/{id}/comments/{commentId}")
    @Operation(summary = "user details", security = @SecurityRequirement(name = "bearer"))
    public void deleteComment(@PathVariable Long id, @PathVariable Long commentId){
        //TODO2

    }

    @PostMapping("/{id}/like")
    @Operation(summary = "user details", security = @SecurityRequirement(name = "bearer"))
    public void like(@PathVariable Long id){
        postService.like(() -> id);
    }

    @DeleteMapping("/{id}/unlike")
    @Operation(summary = "user details", security = @SecurityRequirement(name = "bearer"))
    public void unlike(@PathVariable Long id) {
        //TODO2
    }

}
