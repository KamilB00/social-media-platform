package com.socialmediaplatform.domain.post;


import com.socialmediaplatform.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Post {
    private final Long id;
    private final User author;
    private String content;
    private boolean isEdited;
    private LocalDateTime publishedAt;
    private List<Comment> comments;
    private Set<String> likes;


}
