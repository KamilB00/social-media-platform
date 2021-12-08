package com.socialmediaplatform.domain.post;

import java.util.List;


public interface PostService {
   Post createPost(Command.CreatePost createPostCommand);
   List<Post> getMyFeed();
   void comment(Command.Comment commandComment);
   void like(Command.Like commandLike);
   Post updatePost(Long id, String Content);


    interface Command {
      interface CreatePost extends Command{
          String getContent();
      }

      interface Like extends Command {
          Long getPostId();
      }

      interface Comment extends Command{
          Long getPostId();
          String getContent();
      }
   }

}
