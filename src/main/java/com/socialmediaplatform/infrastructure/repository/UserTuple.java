package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="USERS")
public class UserTuple {
    @Id
    private String username;

    @Size(min = 8)
    private String password;
    private String name;
    private String surname;

    @Column(unique = true ,nullable = false)
    private String email;

    private LocalDateTime dateOfBirth;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> followers;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> following;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<RoleTuple> roles;

    static UserTuple from(User user){
        return  UserTuple.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .followers(user.getFollowers().stream().map(String::valueOf).collect(Collectors.toSet()))
                .following(user.getFollowing().stream().map(String::valueOf).collect(Collectors.toSet()))
                .roles( user.getRoles()==null ? List.of() :
                        user.getRoles().stream().map(RoleTuple::from).collect(Collectors.toList()))
                .build();
    }

    User toDomain(){
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .surname(surname)
                .email(email)
                .dateOfBirth(dateOfBirth)
                .followers(followers.stream().map(String::valueOf).collect(Collectors.toSet()))
                .following(following.stream().map(String::valueOf).collect(Collectors.toSet()))
                .roles(roles == null ?List.of(): roles.stream().map(RoleTuple::toDomain).collect(Collectors.toList()))
                .build();
    }

}
