package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.post.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Builder
@Table(name = "COMMENTS")
@AllArgsConstructor
@NoArgsConstructor

public class CommentTuple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "post"), referencedColumnName = "id")
    private  PostTuple post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "comment_author"), referencedColumnName = "username")
    private UserTuple commentAuthor;

    private LocalDateTime at;

    private String content;

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
