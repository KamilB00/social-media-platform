package com.socialmediaplatform.domain.user;

import lombok.*;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String dateOfBirth;
    private List<Role> roles;
}
