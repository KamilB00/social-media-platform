package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.post.Comment;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;



@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comments")
public class CommentTuple extends BaseTuple{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime at;

    @Size(min = 1, max = 126)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private  PostTuple post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private UserTuple commentAuthor;

    static CommentTuple from(Comment comment){
        return CommentTuple.builder()
                .id(comment.getId())
                .commentAuthor(UserTuple.from(comment.getAuthor()))
                .post(PostTuple.from(comment.getPost()))
                .at(comment.getAt())
                .content(comment.getContent())
                .build();
    }

    Comment toDomain(){
        return Comment.builder()
                .id(id)
                .content(content)
                .at(at)
                .post(post.toDomain())
                .author(commentAuthor.toDomain())
                .build();
    }

}
