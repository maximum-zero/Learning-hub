package org.maximum0.post.aplication.dto;

public record CreateCommentRequestDto(Long postId, Long userId, String content) {

}
