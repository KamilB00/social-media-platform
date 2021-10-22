package com.socialmediaplatform.infrastructure.security.jwt;

import com.socialmediaplatform.domain.user.User;
import com.socialmediaplatform.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserDetails implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(user.get().getPassword())
                    .authorities((GrantedAuthority) user.get().getRoles())
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        }
        throw new UsernameNotFoundException("User '"+ username+"' was not found");
    }
}
