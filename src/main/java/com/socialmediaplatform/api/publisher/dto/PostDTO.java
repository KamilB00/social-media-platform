package com.socialmediaplatform.api.publisher.dto;

import com.socialmediaplatform.domain.post.PostService;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PostDTO implements PostService.Command.CreatePost {
    private boolean isEdited;
    private String content;
    private String authorUsername;
    private String authorEmail;
    private Date publicationDate;
}
