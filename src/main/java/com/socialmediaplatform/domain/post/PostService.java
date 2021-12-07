package com.socialmediaplatform.domain.post;

import java.util.Collection;
import java.util.List;


public interface PostService {
   Post createPost(Command.CreatePost createPostCommand);
   List<Post> getMyFeed();
   void comment(Command.Comment commandComment);


    interface Command {
      interface CreatePost extends Command{
          String getContent();
      }
      interface Comment extends Command{
          Long getPostId();
          String getContent();
      }
   }

}
