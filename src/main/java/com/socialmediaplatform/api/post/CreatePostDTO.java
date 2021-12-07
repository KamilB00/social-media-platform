package com.socialmediaplatform.api.post;

import com.socialmediaplatform.api.user.dto.UserDetailsDTO;
import com.socialmediaplatform.domain.post.Comment;
import com.socialmediaplatform.domain.post.Like;
import com.socialmediaplatform.domain.post.PostService;
import com.socialmediaplatform.domain.post.PostService.Command;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

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
        return new CreatePost() {
            @Override
            public String getContent() {
                return content;
            }
        };
    }
}
