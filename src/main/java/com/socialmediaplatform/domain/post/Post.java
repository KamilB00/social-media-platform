package com.socialmediaplatform.domain.post;


import com.socialmediaplatform.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@RequiredArgsConstructor
public class Post {
    private final Long id;
    private final User author;
    private final String content;
    private final boolean isEdited;
    private final LocalDateTime publishedAt;
    private final List<Comment> comments;
    private final Set<Like> likes;

}
