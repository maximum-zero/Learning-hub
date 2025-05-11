package org.maximum0.post.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.maximum0.post.domain.content.PostPublicationState;
import org.maximum0.user.domain.User;
import org.maximum0.user.domain.UserInfo;

class PostTest {
    private final UserInfo userInfo = new UserInfo("user", "");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);
    private final Post post = Post.createPost(1L, user, "content");

    @Test
    void givenPostCreated_whenLike_thenLikeCountShouldBe1() {
        // When
        post.like(otherUser);

        // Then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenLikeBySelf_thenThrowError() {
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    void givenPostCreatedAndLike_whenUnlike_thenLikeCountShouldBe0() {
        // Given
        post.like(otherUser);

        // When
        post.unlike(otherUser);

        // Then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUnlike_thenLikeCountShouldBe0() {
        // When
        post.unlike(otherUser);

        // Then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated() {
        // Given
        String newPostContent = "new Content";

        // When
        post.updatePost(user, newPostContent, PostPublicationState.PUBLIC);

        // Then
        assertEquals(newPostContent, post.getContent());
    }

    @Test
    void givenPostCreated_whenUpdateOtherUser_thenThrowError() {
        // Given
        String newPostContent = "new Content";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, newPostContent, PostPublicationState.PUBLIC));
    }

}
