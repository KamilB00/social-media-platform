package com.socialmediaplatform.domain.post;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Builder
@RequiredArgsConstructor
public class Like {
    private final String username;
    private final LocalDateTime at;
}
