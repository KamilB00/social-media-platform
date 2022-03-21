package com.socialmediaplatform.domain.post;

import com.socialmediaplatform.domain.user.User;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface PostRepository{
    Post save(Post post);
    Post findById(Long id);
    List<Post> findAllByUser(User user);
    List<Post> findAll();
    List<Post> findAllByUsers(Set<User> followers);
}
