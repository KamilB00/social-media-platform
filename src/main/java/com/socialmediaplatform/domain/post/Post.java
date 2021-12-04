package com.socialmediaplatform.domain.post;


import com.socialmediaplatform.domain.post.interaction.Comment;
import com.socialmediaplatform.domain.user.User;
import lombok.*;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
public class Post {
    private final Long id;
    private final User author;
    private final String content;
    private final Boolean isEdited;
    private final Date publicationDate;
    private final List<Comment> commentList;

}
