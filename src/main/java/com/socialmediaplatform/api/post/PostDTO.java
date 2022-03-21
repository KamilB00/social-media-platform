package com.socialmediaplatform.api.post;

import com.socialmediaplatform.domain.post.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long id;
    private String authorUsername;
    private String content;
    private Set<CommentDTO> comments;
    private Set<String> usersLikes;
    private LocalDateTime publicationDate;
    private Boolean isEdited;


    public static PostDTO fromDomain(Post post) {
        var comments = post.getComments().stream()
                .map(CommentDTO::fromDomain)
                .collect(Collectors.toUnmodifiableSet());


        var likes = post.getLikes().stream()
                .map(String::valueOf)
                .collect(Collectors.toSet());

        return new PostDTO(post.getId(), post.getAuthor().getUsername(), post.getContent(),comments, likes, post.getPublishedAt(), post.isEdited());
    }
}
