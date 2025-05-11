package org.maximum0.post.aplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.maximum0.post.aplication.dto.LikeRequestDto;
import org.maximum0.post.aplication.dto.UpdatePostRequestDto;
import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.content.PostPublicationState;

class PostServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        // When
        Post savedPost = postService.createPost(postRequestDto);

        // Then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(savedPost, post);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatedPost() {
        // Given
        Post savedPost = postService.createPost(postRequestDto);

        // When
        UpdatePostRequestDto updatePostRequestDto = new UpdatePostRequestDto(user.getId(), "this is update content", PostPublicationState.PRIVATE);
        Post updatedPost = postService.updatePost(savedPost.getId(), updatePostRequestDto);

        // Then
        assertEquals(savedPost.getId(), updatedPost.getId());
        assertEquals(updatePostRequestDto.userId(), updatedPost.getAuthor().getId());
        assertEquals(updatePostRequestDto.content(), updatedPost.getContent());
        assertEquals(updatePostRequestDto.state(), updatedPost.getState());
    }

    @Test
    void givenCreatePost_whenLike_thenReturnPostWithLikeShouldBe1() {
        // Given
        Post savedPost = postService.createPost(postRequestDto);

        // When
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // Then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatePost_whenLikeTwice_thenReturnPostWithLikeShouldBe1() {
        // Given
        Post savedPost = postService.createPost(postRequestDto);

        // When
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);
        postService.likePost(likeRequestDto);

        // Then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatePostAndLike_whenUnlike_thenReturnPostWithLikeShouldBe0() {
        // Given
        Post savedPost = postService.createPost(postRequestDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // When
        postService.unlikePost(likeRequestDto);

        // Then
        assertEquals(0, savedPost.getLikeCount());
    }


    @Test
    void givenCreatePost_whenUnlike_thenReturnPostWithLikeShouldBe0() {
        // Given
        Post savedPost = postService.createPost(postRequestDto);

        // When
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);

        // Then
        assertEquals(0, savedPost.getLikeCount());
    }

}
