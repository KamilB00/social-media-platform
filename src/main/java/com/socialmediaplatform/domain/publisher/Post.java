package com.socialmediaplatform.domain.publisher;


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
    private final User author;
    private final String content;
    private final Date publicationDate;
}
