package com.socialmediaplatform.domain.user;

import com.socialmediaplatform.domain.post.Post;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@EqualsAndHashCode(of = "username")
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    private final String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private LocalDateTime dateOfBirth;
    private List<Post> posts;
    private List<Role> roles;
    private Set<String> followers;
    private Set<String> following;

}
