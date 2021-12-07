package com.socialmediaplatform.infrastructure.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.socialmediaplatform.domain.post.Post;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "POSTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostTuple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isEdited;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author", referencedColumnName = "username")
    private UserTuple author;

    private String content;

    private LocalDateTime at;

    static PostTuple from(Post post){
        return PostTuple.builder()
                .author(UserTuple.from(post.getAuthor()))
                .id(post.getId())
                .content(post.getContent())
                .isEdited(post.isEdited())
                .at(post.getPublishedAt())
                .build();
    }
    Post toDomain(){
        return Post.builder()
                .id(id)
                .isEdited(isEdited)
                .author(author.toDomain())
                .content(content)
                .publishedAt(at)
                .build();
    }
}
