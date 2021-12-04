package com.socialmediaplatform.infrastructure.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.socialmediaplatform.domain.post.Post;
import lombok.*;
import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Builder
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor

public class PostTuple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isEdited;
    @ManyToOne
    private UserTuple author;
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern= "dd-MM-yyyy")
    private Date publicationDate;

    static PostTuple from(Post post){
        return PostTuple.builder()
                .author(UserTuple.from(post.getAuthor()))
                .id(post.getId())
                .content(post.getContent())
                .isEdited(post.isEdited())
                .publicationDate(post.getPublicationDate())
                .build();
    }
    Post toDomain(){
        return Post.builder()
                .id(id)
                .isEdited(isEdited)
                .author(author.toDomain())
                .content(content)
                .publicationDate(publicationDate)
                .build();
    }
}
