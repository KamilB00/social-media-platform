package com.socialmediaplatform.api.user.dto;

import com.socialmediaplatform.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDetailsDTO {
    private String username;
    private String name;
    private String surname;
    private LocalDateTime dateOfBirth;
    private String email;
    private Set<String> followers;
    private Set<String> following;


    public static UserDetailsDTO fromDomain(User user) {
        return UserDetailsDTO.builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .followers(user.getFollowers())
                .following(user.getFollowing())
                .build();
    }
}
