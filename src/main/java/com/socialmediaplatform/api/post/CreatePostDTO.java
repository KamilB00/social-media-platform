package com.socialmediaplatform.api.post;

import lombok.*;
import javax.validation.constraints.NotBlank;

import static com.socialmediaplatform.domain.post.PostService.Command.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostDTO {

    @NotBlank
    private String content;

    CreatePost toCommand() {
        return () -> content;
    }
}
