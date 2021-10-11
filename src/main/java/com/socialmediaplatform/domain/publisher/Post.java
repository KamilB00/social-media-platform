package com.socialmediaplatform.domain.publisher;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialmediaplatform.domain.user.User;
import lombok.*;

import java.util.Date;

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
    private final Date publicationDate;

}
