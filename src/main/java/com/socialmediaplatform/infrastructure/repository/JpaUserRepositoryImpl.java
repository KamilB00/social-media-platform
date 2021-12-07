package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.user.User;
import com.socialmediaplatform.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class JpaUserRepositoryImpl implements UserRepository {

    private final JpaUserRepo jpaUserRepo;

    @Override
    public User save(User user) {
        return jpaUserRepo.save(UserTuple.from(user)).toDomain();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserTuple> userTupleOptional = jpaUserRepo.findByUsername(username);
        if(userTupleOptional.isEmpty())
            return Optional.empty();
        return Optional.of(userTupleOptional.orElseThrow().toDomain());
    }

    @Override
    public Set<User> findByUsernames(Set<String> usernames) {
        return jpaUserRepo.findAll().stream()
                .filter(user -> usernames.contains(user.getUsername()))
                .map(UserTuple::toDomain)
                .collect(Collectors.toUnmodifiableSet());
    }

    public interface JpaUserRepo extends JpaRepository<UserTuple, Long> {
        Optional<UserTuple> findByUsername(String username);
        void deleteByUsername(String username);

    }
}
