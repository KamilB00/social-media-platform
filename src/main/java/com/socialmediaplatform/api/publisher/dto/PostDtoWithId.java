package com.socialmediaplatform.api.publisher.dto;
import com.socialmediaplatform.domain.publisher.Post;
import com.socialmediaplatform.domain.publisher.PublisherService;
import lombok.*;

import java.util.Date;

@Getter
@Builder
public class PostDtoWithId implements PublisherService.Command.CreatePost {
    private final Long id;
    private final boolean isEdited;
    private final String content;
    private final String authorUsername;
    private final String authorEmail;
    private final Date publicationDate;

    public static PostDtoWithId fromDomain(Post post) {
        return PostDtoWithId.builder()
                .id(post.getId())
                .isEdited(post.isEdited())
                .content(post.getContent())
                .authorUsername(post.getAuthor().getUsername())
                .authorEmail(post.getAuthor().getEmail())
                .publicationDate(post.getPublicationDate())
                .build();
    }
}
