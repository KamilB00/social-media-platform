package com.socialmediaplatform.domain.post;

import com.socialmediaplatform.domain.user.User;
import com.socialmediaplatform.domain.user.UserRepository;
import com.socialmediaplatform.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
        public Post createPost(Command.CreatePost createPostCommand) {
        if (createPostCommand.getContent().isBlank())
            throw new IllegalArgumentException("Post has no content");

        Post post = Post.builder()
                .author(userService.whoAmI())
                .content(createPostCommand.getContent())
                .isEdited(false)
                .comments(new HashSet<>())
                .publishedAt(LocalDateTime.now())
                .likes(new HashSet<>())
                .build();

            return postRepository.save(post);
        }


    @Override
    public void comment(Command.Comment commandComment) {
        Post post = postRepository.findById(commandComment.getPostId());
        User author = userService.whoAmI();
        post.getComments().add(new Comment(null,author, post, commandComment.getContent(), LocalDateTime.now()));
        postRepository.save(post);
    }

    @Override
    public void like(Command.Like commandLike) {
        Post post = postRepository.findById(commandLike.getPostId());
        User user = userService.whoAmI();
        post.getLikes().add(user.getId());
        postRepository.save(post);
    }

    @Override
    public Post updatePost(Long id, String content) {
        Post post = postRepository.findById(id);

        if(post.getAuthorsName().equals(userService.whoAmI().getUsername())){
            post.updateContent(content);
        }
        return postRepository.save(post);
    }

    @Override
    public List<Post> getMyFeed() {
        User user = userService.whoAmI();
        Set<User> following = userRepository.findByUsernames(user.getFollowing().stream()
                .map(String::valueOf)
                .collect(Collectors.toUnmodifiableSet()));
        return postRepository.findAllByUsers(following);
    }

}



