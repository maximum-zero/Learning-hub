package org.maximum0.post.aplication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.maximum0.post.aplication.dto.LikeRequestDto;
import org.maximum0.post.aplication.dto.UpdateCommentRequestDto;
import org.maximum0.post.domain.comment.Comment;

class CommentServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenCreateComment_whenCreateComment_thenReturnComment() {
        // When
        Comment comment = commentService.createComment(commentRequestDto);

        // Then
        assertEquals(commentContent, comment.getContent());
    }

    @Test
    void givenCreateComment_whenUpdateComment_thenReturnUpdatedComment() {
        // Given
        Comment comment = commentService.createComment(commentRequestDto);

        // When
        String updateCommentContent = "this is update test content";
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(comment.getId(), user.getId(), updateCommentContent);
        Comment updatedComment = commentService.updateComment(updateCommentRequestDto);

        // Then
        assertEquals(updateCommentContent, updatedComment.getContent());
    }

    @Test
    void givenCreateComment_whenLikeComment_thenReturnCommentWithLikeShouldBe1() {
        // Given
        Comment comment = commentService.createComment(commentRequestDto);

        // When
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        // Then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCreateCommentAndLike_whenUnlikeComment_thenReturnCommentWithLikeShouldBe0() {
        // Given
        Comment comment = commentService.createComment(commentRequestDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        // When
        commentService.unlikeComment(likeRequestDto);

        // Then
        assertEquals(0, comment.getLikeCount());
    }

}
