package com.socialmediaplatform.domain.post;

import com.socialmediaplatform.domain.user.Follow;
import com.socialmediaplatform.domain.user.User;
import com.socialmediaplatform.domain.user.UserRepository;
import com.socialmediaplatform.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
                //.comments(new ArrayList<>())
                .publishedAt(LocalDateTime.now())
                //.likes(new HashSet<>())
                .build();

            return postRepository.save(post);
        }

    @Override
    public void comment(Command.Comment commandComment) {
//        Post post = postRepository.findById(commandComment.getPostId()).orElseThrow();
//        User author = userService.whoAmI();
//        post.getComments().add(new Comment(author, commandComment.getContent(), LocalDateTime.now()));
//        postRepository.save(post);
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



