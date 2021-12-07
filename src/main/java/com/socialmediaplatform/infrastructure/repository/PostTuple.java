package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.post.Post;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<CommentTuple> comments;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<LikeEntity> likes;

    private String content;

    private LocalDateTime at;

    static PostTuple from(Post post){
        return PostTuple.builder()
                .author(UserTuple.from(post.getAuthor()))
                .id(post.getId())
                .content(post.getContent())
                .isEdited(post.isEdited())
                .at(post.getPublishedAt())
                .comments(post.getComments() == null ? List.of() : post.getComments().stream().map(CommentTuple::from).collect(Collectors.toList()))
                .build();
    }
    Post toDomain(){
        return Post.builder()
                .id(id)
                .isEdited(isEdited)
                .author(author.toDomain())
                .content(content)
                .comments(comments == null ? List.of() : comments.stream().map(CommentTuple::toDomain).collect(Collectors.toList()))
                .publishedAt(at)
                .build();
    }
}
