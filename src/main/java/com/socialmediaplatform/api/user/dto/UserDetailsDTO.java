package com.socialmediaplatform.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailsDTO {
    private String username;
    private String name;
    private String surname;
    private String email;
}
