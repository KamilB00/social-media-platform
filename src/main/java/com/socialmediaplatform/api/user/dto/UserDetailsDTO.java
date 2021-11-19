package com.socialmediaplatform.api.user.dto;

import com.socialmediaplatform.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDetailsDTO {
    private String username;
    private String name;
    private String surname;
    private String email;

    // to delete
    public static UserDetailsDTO fromDomain(User user) {
        return UserDetailsDTO.builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
    }
}
