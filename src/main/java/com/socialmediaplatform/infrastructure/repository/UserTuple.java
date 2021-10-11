package com.socialmediaplatform.infrastructure.repository;


import com.socialmediaplatform.domain.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="users")

public class UserTuple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;
    @Size(min = 8)
    private String password;
    private String name;
    private String surname;
    @Column(unique = true,nullable = false)
    private String email;
   private String dateOfBirth;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<RoleTuple> roles;

    static UserTuple from(User user){
        return new UserTuple(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getDateOfBirth(),
                user.getRoles()==null ? List.of() :
                        user.getRoles().stream().map(RoleTuple::from).collect(Collectors.toList())
                );
    }
    User toDomain(){
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .name(name)
                .surname(surname)
                .email(email)
                .dateOfBirth(dateOfBirth)
                .roles(roles == null ?List.of(): roles.stream().map(RoleTuple::toDomain).collect(Collectors.toList()))
                .build();
    }

}
