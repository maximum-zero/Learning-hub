package org.maximum0.post.aplication;

import org.maximum0.post.aplication.dto.CreatePostRequestDto;
import org.maximum0.post.aplication.dto.LikeRequestDto;
import org.maximum0.post.aplication.dto.UpdatePostRequestDto;
import org.maximum0.post.aplication.interfaces.LikeRepository;
import org.maximum0.post.aplication.interfaces.PostRepository;
import org.maximum0.post.domain.Post;
import org.maximum0.user.application.UserService;
import org.maximum0.user.domain.User;

public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository,
            LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    public Post createPost(CreatePostRequestDto request) {
        User author = userService.getUser(request.userId());
        Post post = Post.createPost(null, author, request.content(), request.state());
        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequestDto request) {
        Post post = getPost(request.postId());
        User author = userService.getUser(request.userId());

        post.updatePost(author, request.content(), request.state());
        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto request) {
        Post post = getPost(request.targetId());
        User user = userService.getUser(request.userId());

        if (likeRepository.checkedLike(post, user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }


    public void unlikePost(LikeRequestDto request) {
        Post post = getPost(request.targetId());
        User user = userService.getUser(request.userId());

        if (likeRepository.checkedLike(post, user)) {
            post.unlike(user);
            likeRepository.unlike(post, user);
        }
    }

}
