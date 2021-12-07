package com.socialmediaplatform.infrastructure.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private  PostTuple postId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author", referencedColumnName = "username")
    private UserTuple author;

    private LocalDateTime at;

    private String content;

}
