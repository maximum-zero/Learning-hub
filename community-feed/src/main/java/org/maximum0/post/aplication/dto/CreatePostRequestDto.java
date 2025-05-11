package org.maximum0.post.aplication.dto;

import org.maximum0.post.domain.content.PostPublicationState;

public record CreatePostRequestDto(Long userId, String content, PostPublicationState state) {

}
