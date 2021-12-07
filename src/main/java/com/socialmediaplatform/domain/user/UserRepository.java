package com.socialmediaplatform.domain.user;

import java.util.Optional;
import java.util.Set;

public interface UserRepository {
    User save (User user);
    Optional<User> findByUsername(String username);
    Set<User> findByUsernames(Set<String> usernames);
}
