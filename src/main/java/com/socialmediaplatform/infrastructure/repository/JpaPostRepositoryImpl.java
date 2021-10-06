package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.post.Post;
import com.socialmediaplatform.domain.post.PostRepository;
import com.socialmediaplatform.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JpaPostRepositoryImpl implements PostRepository {
    private final JpaPostRepo jpaPostRepo;

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Post> findByIdAndUser(Long id, User user) {
        return Optional.empty();
    }

    @Override
    public List<Post> findAllByUser(User user) {
        return null;
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public void delete(Post post) {

    }

    public interface JpaPostRepo extends JpaRepository<PostTuple, Long> {
        List<PostTuple> findAllByUser(UserTuple user);
        Optional<PostTuple> findAllByIdAndUser(Long id, UserTuple user);
    }
}
