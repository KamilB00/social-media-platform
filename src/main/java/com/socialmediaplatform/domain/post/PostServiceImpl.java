package com.socialmediaplatform.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
        public Post createPost(Command.CreatePost createPostCommand) {
        if (createPostCommand.getContent().isBlank())
            throw new IllegalArgumentException("Post has no content");

        Post post = Post.builder()
                .content(createPostCommand.getContent())
                .isEdited(createPostCommand.isEdited())
                .publicationDate(createPostCommand.getPublicationDate())
                .build();

            return postRepository.save(post);
        }

        @Override
        public List<Post> getAllPosts() {
            return postRepository.findAll();
        }

}

