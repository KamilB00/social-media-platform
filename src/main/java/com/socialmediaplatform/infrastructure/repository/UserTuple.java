package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "users")
public class UserTuple extends BaseTuple implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    @Size(min = 8, max = 30, message = "Password must have between 8 and 30 characters")
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @Column(unique = true, nullable = false)
    @Email(message = "Invalid email format")
    private String email;

    @NotNull
    private LocalDate dateOfBirth;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<PostTuple> posts;

    @OneToMany(
            mappedBy = "commentAuthor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CommentTuple> comments;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> followers;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> following;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<RoleTuple> roles;


    static UserTuple from(User user) {
        return UserTuple.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .comments(user.getComments().stream().map(CommentTuple::from).collect(Collectors.toSet()))
                .posts(user.getPosts().stream().map(PostTuple::from).collect(Collectors.toSet()))
                .followers(user.getFollowers().stream().map(String::valueOf).collect(Collectors.toSet()))
                .following(user.getFollowing().stream().map(String::valueOf).collect(Collectors.toSet()))
                .roles(user.getRoles() == null ? List.of() :
                        user.getRoles().stream().map(RoleTuple::from).collect(Collectors.toList()))
                .build();
    }

    User toDomain() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .name(name)
                .surname(surname)
                .email(email)
                .dateOfBirth(dateOfBirth)
                .comments(comments == null ? new HashSet<>() : comments.stream().map(CommentTuple::toDomain).collect(Collectors.toSet()))
                .posts(posts == null ? new HashSet<>() : posts.stream().map(PostTuple::toDomain).collect(Collectors.toSet()))
                .followers(followers == null ? new HashSet<>() : followers.stream().map(String::valueOf).collect(Collectors.toSet()))
                .following(following == null ? new HashSet<>() : following.stream().map(String::valueOf).collect(Collectors.toSet()))
                .roles(roles == null ? List.of() : roles.stream().map(RoleTuple::toDomain).collect(Collectors.toList()))
                .build();
    }
}
