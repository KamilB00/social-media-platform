package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.post.Post;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "posts")
public class PostTuple extends BaseTuple {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private boolean isEdited;
    
    @Size(min = 1, max = 256)
    private String content;

    @NotNull
    private LocalDateTime at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private UserTuple author;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<CommentTuple> comments;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> likes;

    static PostTuple from(Post post){
        return PostTuple.builder()
                .id(post.getId())
                .author(UserTuple.from(post.getAuthor()))
                .content(post.getContent())
                .isEdited(post.isEdited())
                .at(post.getPublishedAt())
                .comments(post.getComments() == null ? new HashSet<>() : post.getComments().stream().map(CommentTuple::from).collect(Collectors.toSet()))
                .likes(post.getLikes() == null ? new HashSet<>() : post.getLikes().stream().map(String::valueOf).collect(Collectors.toSet()))
                .build();
    }

    Post toDomain(){
        return Post.builder()
                .id(id)
                .isEdited(isEdited)
                .author(author.toDomain())
                .content(content)
                .comments(comments == null ? new HashSet<>() : comments.stream().map(CommentTuple::toDomain).collect(Collectors.toSet()))
                .likes(likes == null ? new HashSet<>() : likes.stream().map(Long::valueOf).collect(Collectors.toSet()))
                .publishedAt(at)
                .build();
    }
}
