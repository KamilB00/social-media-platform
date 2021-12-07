package com.socialmediaplatform.domain.post;

import com.socialmediaplatform.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Comment {
    private Long id;
    private final User author;
    private final String content;
    private final LocalDateTime at;
}
