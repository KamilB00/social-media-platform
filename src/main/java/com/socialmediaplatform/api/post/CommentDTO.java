package com.socialmediaplatform.api.post;

import com.socialmediaplatform.domain.post.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    String authorUsername;
    String content;
    LocalDateTime at;

    public static CommentDTO fromDomain(Comment comment) {
        return new CommentDTO(comment.getAuthor().getUsername(), comment.getContent(), comment.getAt());
    }
}
