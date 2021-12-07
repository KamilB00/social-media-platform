package com.socialmediaplatform.api.user.dto;

import com.socialmediaplatform.domain.user.UserService;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime dateOfBirth;
    private String email;
}
