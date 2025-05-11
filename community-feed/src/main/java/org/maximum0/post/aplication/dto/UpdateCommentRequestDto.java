package org.maximum0.post.aplication.dto;

public record UpdateCommentRequestDto(Long commentId, Long userId, String content) {

}
