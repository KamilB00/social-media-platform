package com.socialmediaplatform.domain.publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
   Post createPost(Post post);
   List<Post> getAllPosts();

}
