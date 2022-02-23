package com.socialmediaplatform.domain.user;

import com.socialmediaplatform.domain.post.Comment;
import com.socialmediaplatform.domain.post.Post;
import lombok.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private LocalDate dateOfBirth;
    private List<Role> roles;
    private Set<Post> posts;
    private Set<Comment> comments;
    private Set<String> followers;
    private Set<String> following;

}
