package org.maximum0.post.ui;

import lombok.RequiredArgsConstructor;
import org.maximum0.common.ui.Response;
import org.maximum0.post.aplication.CommentService;
import org.maximum0.post.aplication.dto.CreateCommentRequestDto;
import org.maximum0.post.aplication.dto.CreatePostRequestDto;
import org.maximum0.post.aplication.dto.LikeRequestDto;
import org.maximum0.post.aplication.dto.UpdateCommentRequestDto;
import org.maximum0.post.aplication.dto.UpdatePostRequestDto;
import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.comment.Comment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @RequestMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto requestDto) {
        Comment comment = commentService.createComment(requestDto);
        return Response.ok(comment.getId());
    }

    @PutMapping("/{commentId}")
    public Response<Long> updateComment(@PathVariable(name = "commentId") Long commentId, @RequestBody UpdateCommentRequestDto requestDto) {
        Comment comment = commentService.updateComment(commentId, requestDto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> likePost(@RequestBody LikeRequestDto requestDto) {
        commentService.likeComment(requestDto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikePost(@RequestBody LikeRequestDto requestDto) {
        commentService.unlikeComment(requestDto);
        return Response.ok(null);
    }

}
