package com.socialmediaplatform.domain.post;

import com.socialmediaplatform.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Comment {
    private final Long id;
    private User author;
    private Post post;
    private String content;
    private LocalDateTime at;
}
