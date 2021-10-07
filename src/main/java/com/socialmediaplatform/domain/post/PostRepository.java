package com.socialmediaplatform.domain.post;

import com.socialmediaplatform.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository{
    Post save(Post post);
    Optional<Post> findById(Long id);
    Optional<Post> findByIdAndUser(Long id, User user);
    List<Post> findAllByUser(User user);
    List<Post> findAll();
    void delete(Post post);


}
