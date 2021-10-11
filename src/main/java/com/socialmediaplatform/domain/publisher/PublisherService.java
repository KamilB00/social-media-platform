package com.socialmediaplatform.domain.publisher;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface PublisherService {
   Post createPost(Command.CreatePost createPostCommand);
   List<Post> getAllPosts();
   String hello();

   interface Command {
      interface CreatePost extends Command{
        boolean isEdited();
        String getContent();
        Date getPublicationDate();
      }
   }

}
