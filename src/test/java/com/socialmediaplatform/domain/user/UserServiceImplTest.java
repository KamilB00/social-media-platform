package com.socialmediaplatform.domain.user;

import com.socialmediaplatform.api.user.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@ContextConfiguration
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldCreateUser() {
        UserDTO userDTO = UserDTO.builder()
                .username("example")
                .password("example")
                .name("Name")
                .surname("Surname")
                .dateOfBirth(LocalDateTime.of(2000,01,01,12,0,0))
                .email("example@email.pl")
                .build();

        User user = User.builder()
                .username("example")
                .password("example")
                .name("Name")
                .surname("Surname")
                .dateOfBirth(LocalDateTime.of(2000,01,01,12,0,0))
                .email("example@email.pl")
                .roles(List.of(Role.ROLE_CLIENT))
                .followers(Collections.emptySet())
                .following(Collections.emptySet())
                .build();

        given(userRepository.findByUsername(userDTO.getUsername())).willReturn(Optional.empty());
        given(passwordEncoder.encode(userDTO.getPassword())).willReturn(user.getPassword());

        when(userRepository.save(any(User.class))).thenAnswer(i->i.getArguments()[0]);

        User createdUser = userService.createUser(userDTO);

        assertEquals(user.getUsername(),createdUser.getUsername());
        assertEquals(user.getPassword(), createdUser.getPassword());
        assertEquals(user.getName(), createdUser.getName());
        assertEquals(user.getSurname(), createdUser.getSurname());
        assertEquals(user.getDateOfBirth(), createdUser.getDateOfBirth());
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getRoles(),createdUser.getRoles());
        assertEquals(user.getFollowers(),createdUser.getFollowers());
        assertEquals(user.getFollowing(), createdUser.getFollowing());
    }

    @Test
    void shouldNotCreateExistingUser() {
        UserDTO userDTO = UserDTO.builder()
                .username("example")
                .build();
        User user = User.builder().build();

        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(Optional.of(user));

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(userDTO));
    }
}