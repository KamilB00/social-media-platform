package com.socialmediaplatform.domain.post;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialmediaplatform.domain.user.User;
import lombok.*;

@Getter
@Builder
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
public class Post {
    private final Long id;
    private final boolean isEdited;
    @JsonIgnore
    private final User user;
    private final String content;
    private final String publicationDate;

}
