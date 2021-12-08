package com.socialmediaplatform.infrastructure.repository;

import com.socialmediaplatform.domain.post.Post;
import com.socialmediaplatform.domain.post.PostRepository;
import com.socialmediaplatform.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        return Optional.of(jpaPostRepo.findAllById(id).orElseThrow().toDomain());
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

    @Override
    public List<Post> findAllByUsers(Set<User> followers) {
        Set<String> usernames = followers.stream().map(User::getUsername).collect(Collectors.toUnmodifiableSet());
        return jpaPostRepo.findAll().stream()
                .filter(post -> usernames.contains(post.getAuthor().getUsername()))
                .map(PostTuple::toDomain).collect(Collectors.toUnmodifiableList());
    }


    public interface JpaPostRepo extends JpaRepository<PostTuple, Long> {
        List<PostTuple> findAllByAuthor(UserTuple author);
        Optional<PostTuple> findAllById(Long id);
        Optional<PostTuple> findAllByIdAndAuthor(Long id, UserTuple author);
    }
}
