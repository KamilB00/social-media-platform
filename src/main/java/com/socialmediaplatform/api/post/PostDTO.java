package com.socialmediaplatform.api.post;

import com.socialmediaplatform.domain.post.Comment;
import com.socialmediaplatform.domain.post.Like;
import com.socialmediaplatform.domain.post.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long id;
    private String authorUsername;
    private String content;
    private List<CommentDTO> comments;
    private Set<String> usersLikes;
    private LocalDateTime publicationDate;
    private Boolean isEdited;


    public static PostDTO fromDomain(Post post) {
        var comments = post.getComments().stream()
                .map(CommentDTO::fromDomain)
                .collect(toUnmodifiableList());


        var likes = post.getLikes().stream()
                .map(Like::getUsername)
                .collect(Collectors.toUnmodifiableSet());

        return new PostDTO(post.getId(), post.getAuthor().getUsername(), post.getContent(),comments, likes, post.getPublishedAt(), post.isEdited());
    }
}
