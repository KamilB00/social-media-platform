package com.socialmediaplatform.domain.publisher;

import java.util.Date;
import java.util.List;

public interface PublisherService {
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
