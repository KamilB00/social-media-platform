package com.socialmediaplatform.domain.post;


import com.socialmediaplatform.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Post {
    private final Long id;
    private final User author;
    private String content;
    private boolean isEdited;
    private LocalDateTime publishedAt;
    private Set<Comment> comments;
    private Set<Long> likes;

    void updateContent(String content) {
        this.content = content;
        isEdited = true;
        publishedAt = LocalDateTime.now();
    }
    String getTitle() {
        return author.getName() + " " + content.substring(10) + "... " + publishedAt.toString();
    }

    String getAuthorsName() {
        return author.getName();
    }

}
