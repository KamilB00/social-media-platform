package com.socialmediaplatform.domain.post;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PostServiceImpl implements PostService {

        private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
        public Post createPost(Post post) {
            return postRepository.save(post);
        }

        @Override
        public List<Post> getAllPosts() {
            return (List<Post>) postRepository.findAll();
        }

        @Override
        public Optional<Post> getPostById(Long postId) {
            return postRepository.findById(postId);
        }

        @Override
        public Post updatePost(Post post) {
            Optional<Post> p = postRepository.findById(post.getId());
            if(p.isPresent()){
               //update post
            }


          //  return postRepository.save(p);
        }
        @Override
        public void deletePost(Post toDelete) {
            postRepository.delete(toDelete);
        }
    }


