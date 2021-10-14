package com.socialmediaplatform.api.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class UserLoginDTO {
    private String username;
    private String password;
}
