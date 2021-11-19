package com.socialmediaplatform.domain.user;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode(of = "username")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Date dateOfBirth;
    private List<Role> roles;
}
