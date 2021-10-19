package com.socialmediaplatform.domain.user;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode(of = "username")
@RequiredArgsConstructor
public class User {
    private final String username;
    private final String password;
    private final String name;
    private final String surname;
    private final String email;
    private final Date dateOfBirth;
    private final List<Role> roles;
}
