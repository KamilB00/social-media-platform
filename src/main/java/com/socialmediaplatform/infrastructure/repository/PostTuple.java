package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.post.Post;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "author"), referencedColumnName = "username")
    private UserTuple author;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<CommentTuple> comments;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> likes;
    

    private String content;

    private LocalDateTime at;

    static PostTuple from(Post post){
        return PostTuple.builder()
                .author(UserTuple.from(post.getAuthor()))
                .id(post.getId())
                .content(post.getContent())
                .isEdited(post.isEdited())
                .at(post.getPublishedAt())
                .comments(post.getComments() == null ? new ArrayList<>() : post.getComments().stream().map(CommentTuple::from).collect(Collectors.toList()))
                .likes(post.getLikes() == null ? new HashSet<>() : post.getLikes().stream().map(String::valueOf).collect(Collectors.toSet()))
                .build();
    }

    Post toDomain(){
        return Post.builder()
                .id(id)
                .isEdited(isEdited)
                .author(author.toDomain())
                .content(content)
                .comments(comments == null ? new ArrayList<>() : comments.stream().map(CommentTuple::toDomain).collect(Collectors.toList()))
                .likes(likes == null ? new HashSet<>() : likes.stream().map(String::valueOf).collect(Collectors.toSet()))
                .publishedAt(at)
                .build();
    }
}
