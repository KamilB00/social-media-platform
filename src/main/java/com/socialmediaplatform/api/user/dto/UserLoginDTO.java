package com.socialmediaplatform.api.user.dto;

import com.socialmediaplatform.domain.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserLoginDTO implements UserService.Query.Login {
    private String username;
    private String password;
}
