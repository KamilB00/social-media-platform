package com.socialmediaplatform.domain.post;

import java.util.Date;
import java.util.List;

public interface PostService {
   Post createPost(Command.CreatePost createPostCommand);
   List<Post> getAllPosts();

   interface Command {
      interface CreatePost extends Command{
        boolean isEdited();
        String getContent();
        Date getPublicationDate();
      }
   }

}
