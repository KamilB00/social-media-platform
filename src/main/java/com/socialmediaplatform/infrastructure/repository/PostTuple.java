package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.publisher.Post;
import lombok.*;
import javax.persistence.*;


@Data
@Entity
@Builder
@Table(name = "posts")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class PostTuple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isEdited;
    @ManyToOne
    private UserTuple user;
    private String content;
    private String publicationDate;

    static PostTuple from(Post post){
        return new PostTuple(
                post.getId(),
                post.isEdited(),
                UserTuple.from(post.getUser()),
                post.getContent(),
                post.getPublicationDate()
                );
    }
    Post toDomain(){
        return Post.builder()
                .id(id)
                .isEdited(isEdited)
                .user(user.toDomain())
                .content(content)
                .publicationDate(publicationDate)
                .build();
    }
}
