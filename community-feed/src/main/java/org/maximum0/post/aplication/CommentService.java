package org.maximum0.post.aplication;

import org.maximum0.post.aplication.dto.CreateCommentRequestDto;
import org.maximum0.post.aplication.dto.LikeRequestDto;
import org.maximum0.post.aplication.dto.UpdateCommentRequestDto;
import org.maximum0.post.aplication.interfaces.CommentRepository;
import org.maximum0.post.aplication.interfaces.LikeRepository;
import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.comment.Comment;
import org.maximum0.user.application.UserService;
import org.maximum0.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public CommentService(UserService userService, PostService postService,
            CommentRepository commentRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id);
    }

    public Comment createComment(CreateCommentRequestDto request) {
        Post post = postService.getPost(request.postId());
        User author = userService.getUser(request.userId());

        Comment comment = Comment.createComment(post, author, request.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, UpdateCommentRequestDto request) {
        Comment comment = getComment(commentId);
        User user = userService.getUser(request.userId());

        comment.updateComment(user, request.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto request) {
        Comment comment = getComment(request.targetId());
        User user = userService.getUser(request.userId());

        if (likeRepository.checkedLike(comment, user)) {
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikeComment(LikeRequestDto request) {
        Comment comment = getComment(request.targetId());
        User user = userService.getUser(request.userId());

        if (likeRepository.checkedLike(comment, user)) {
            comment.unlike(user);
            likeRepository.unlike(comment, user);
        }
    }

}
