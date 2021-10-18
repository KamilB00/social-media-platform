package com.socialmediaplatform.domain.user;

import com.socialmediaplatform.api.user.dto.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(Command.CreateUser createUserCommand) {
        if (userRepository.findByUsername(createUserCommand.getUsername()).isPresent())
            throw new IllegalArgumentException("User with typed username already exists");

        LinkedList<Role> roles = new LinkedList<>();
        roles.add(Role.ROLE_CLIENT);
        User user = User.builder()
                .username(createUserCommand.getUsername())
                .password(createUserCommand.getPassword()) // TODO Password encode
                .roles(roles)
                .dateOfBirth(createUserCommand.getDateOfBirth())
                .name(createUserCommand.getName())
                .surname(createUserCommand.getSurname())
                .email(createUserCommand.getEmail())
                .build();
        return userRepository.save(user);
    }

    @Override
    public String login(Query.Login login) {
        return null;
    }

    @Override
    public UserDetailsDTO getUserDetails() {
        return null;
    }

    @Override
    public List<UserDetailsDTO> getAllUsers() {

       return userRepository.findAll().stream()
                .map(user -> {
               return UserDetailsDTO.fromDomain(user);
                }).collect(Collectors.toList());
    }
}