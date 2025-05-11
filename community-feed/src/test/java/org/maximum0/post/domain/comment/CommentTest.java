package org.maximum0.post.domain.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.content.CommentContent;
import org.maximum0.post.domain.content.PostContent;
import org.maximum0.user.domain.User;
import org.maximum0.user.domain.UserInfo;

class CommentTest {
    private final UserInfo userInfo = new UserInfo("user", "");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);
    private final Post post = new Post(1L, user, new PostContent("content"));
    private final Comment comment = new Comment(1L, post, user, new CommentContent("comment content"));

    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1() {
        // When
        comment.like(otherUser);

        // Then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenLikeBySelf_thenThrowError() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreateAndLike_whenUnlike_thenCountShouldBe0() {
        // Given
        comment.like(otherUser);

        // When
        comment.unlike(otherUser);

        // Then
        assertEquals(0, comment.getLikeCount());
    }


    @Test
    void givenCommentCreate_whenUnlike_thenCountShouldBe0() {
        // When
        comment.unlike(otherUser);

        // Then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenUpdateContent_thenContentShouldBeUpdated() {
        // Given
        String newCommentContent = "new comment";

        // When
        comment.updateComment(user, newCommentContent);

        // Then
        assertEquals(newCommentContent, comment.getContent());
    }


    @Test
    void givenCommentCreated_whenUpdateOtherUser_thenThrowError() {
        // Given
        String newCommentContent = "new Content";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(otherUser, newCommentContent));
    }

}
