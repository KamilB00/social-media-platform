package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.publisher.Post;
import com.socialmediaplatform.domain.publisher.PostRepository;
import com.socialmediaplatform.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaPostRepositoryImpl implements PostRepository {
    private final JpaPostRepo jpaPostRepo;

    public JpaPostRepositoryImpl(JpaPostRepo jpaPostRepo) {
        this.jpaPostRepo = jpaPostRepo;
    }

    @Override
    public Post save(Post post) {
        return jpaPostRepo.save(PostTuple.from(post)).toDomain();
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
        List<PostTuple> findAllByAuthor(UserTuple author);
        Optional<PostTuple> findAllByIdAndAuthor(Long id, UserTuple author);
    }
}
