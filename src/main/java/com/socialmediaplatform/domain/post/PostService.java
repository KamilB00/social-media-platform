package com.socialmediaplatform.domain.post;

import com.socialmediaplatform.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface PostService {
   Post createPost(Post post);
   List<Post> getAllPosts();
   Optional<Post> getPostById(Long id);
   Post updatePost(Post toUpdate);
   void deletePost(Post id);
}
