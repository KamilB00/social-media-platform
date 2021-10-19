package com.socialmediaplatform.api.user.dto;

import com.socialmediaplatform.domain.user.UserService;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements UserService.Command.CreateUser {
    private String username;
    private String password;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String email;
}
