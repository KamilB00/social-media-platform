package com.socialmediaplatform.domain.user;

import com.socialmediaplatform.api.user.dto.UserDetailsDTO;
import com.socialmediaplatform.infrastructure.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public User createUser(Command.CreateUser createUserCommand) {
        if (userRepository.findByUsername(createUserCommand.getUsername()).isPresent())
            throw new IllegalArgumentException("User with typed username already exists");

        LinkedList<Role> roles = new LinkedList<>();
        roles.add(Role.ROLE_CLIENT);
        User user = User.builder()
                .username(createUserCommand.getUsername())
                .password(passwordEncoder.encode(createUserCommand.getPassword()))
                .roles(roles)
                .dateOfBirth(createUserCommand.getDateOfBirth())
                .name(createUserCommand.getName())
                .surname(createUserCommand.getSurname())
                .email(createUserCommand.getEmail())
                .build();
        return userRepository.save(user);
    }

    @Override
    public User whoAmI() {
        return search(() -> SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public String login(Query.Login login) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        return jwtProvider.createToken(login.getUsername(), userRepository.findByUsername(login.getUsername()).get().getRoles());
    }

    @Override
    public UserDetailsDTO getUserDetails() {
       User user = whoAmI();
        return UserDetailsDTO.fromDomain(user);
    }


    public User search(Query.Search querySearch){
        return userRepository.findByUsername(querySearch.getUsername()).orElseThrow();
    }


}