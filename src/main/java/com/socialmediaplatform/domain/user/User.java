package com.socialmediaplatform.domain.user;

import lombok.*;


import java.time.LocalDate;
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
    private LocalDate dateOfBirth;
    private List<Role> roles;
    private Set<String> followers;
    private Set<String> following;

}
