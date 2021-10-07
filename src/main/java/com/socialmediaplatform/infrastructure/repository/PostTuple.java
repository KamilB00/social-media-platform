package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.post.Post;
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
}
