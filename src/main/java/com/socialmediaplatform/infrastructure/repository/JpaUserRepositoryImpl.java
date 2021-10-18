package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.user.User;
import com.socialmediaplatform.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JpaUserRepositoryImpl implements UserRepository {

    private final JpaUserRepo jpaUserRepo;

    @Override
    public User save(User user) {
        return jpaUserRepo.save(UserTuple.from(user)).toDomain();
    }

    @Override
    public Optional<User> findById(Long id) {
        //TODO
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        // TODO
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<UserTuple> userTupleList = jpaUserRepo.findAll();
        if (userTupleList.isEmpty())
            return List.of();
        return userTupleList.stream().map(UserTuple::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteByUsername(String username) {
        // TODO
    }

    public interface JpaUserRepo extends JpaRepository<UserTuple, Long> {

    }
}
