package com.socialmediaplatform.domain.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

        private final PostRepository postRepository;

    @Override
        public Post createPost(Post post) {
            return postRepository.save(post);
        }

        @Override
        public List<Post> getAllPosts() {
            return (List<Post>) postRepository.findAll();
        }

    }


