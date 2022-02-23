package com.socialmediaplatform.domain.user;

import com.socialmediaplatform.api.user.dto.UserDTO;
import com.socialmediaplatform.api.user.dto.UserDetailsDTO;
import com.socialmediaplatform.infrastructure.security.AES;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
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
                .dateOfBirth(LocalDate.of(2000,1,1))
                .email("example@email.pl")
                .build();

        User user = User.builder()
                .username("example")
                .password("example")
                .name("Name")
                .surname("Surname")
                .dateOfBirth(LocalDate.of(2000,1,1))
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

    @Test
    @WithMockUser(username = "user", password = "pass")
    void shouldReturnUserDetails() {

        User user = User.builder()
                .username("user")
                .password("pass")
                .name("Name")
                .surname("Surname")
                .dateOfBirth(LocalDate.of(2000,01,01))
                .email("example@email.pl")
                .roles(List.of(Role.ROLE_CLIENT))
                .followers(Collections.emptySet())
                .following(Collections.emptySet())
                .build();

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        given(securityContext.getAuthentication()).willReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        given(userRepository.findByUsername(any(String.class))).willReturn(Optional.of(user));
        given(SecurityContextHolder.getContext().getAuthentication().getName()).willReturn(user.getUsername());

        UserDetailsDTO actualUserDetails = userService.getUserDetails();

        assertEquals(user.getUsername(),actualUserDetails.getUsername());
        assertEquals(user.getName(),actualUserDetails.getName());
        assertEquals(user.getSurname(),actualUserDetails.getSurname());
        assertEquals(user.getDateOfBirth(), actualUserDetails.getDateOfBirth());
        assertEquals(user.getEmail(), actualUserDetails.getEmail());
        assertEquals(user.getFollowing(), actualUserDetails.getFollowing());
        assertEquals(user.getFollowers(), actualUserDetails.getFollowers());
    }
}