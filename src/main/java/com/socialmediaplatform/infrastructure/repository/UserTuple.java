package com.socialmediaplatform.infrastructure.repository;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.socialmediaplatform.domain.user.User;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
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
    private String username;

    @Size(min = 8)
    private String password;
    private String name;
    private String surname;

    @Column(unique = true,nullable = false)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern= "yyyy-MM-dd")
    private Date dateOfBirth;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<RoleTuple> roles;

    static UserTuple from(User user){
        return new UserTuple(
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
