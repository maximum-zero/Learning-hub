package org.maximum0.post.aplication.dto;

import org.maximum0.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long userId, String content, PostPublicationState state) {

}
