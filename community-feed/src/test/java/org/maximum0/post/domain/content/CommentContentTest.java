package org.maximum0.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommentContentTest {

    @Test
    void givenContentLengthThisOk_whenCreatedCommentContent_thenReturnTextContent() {
        // Given
        String text = "this is a test";

        // When
        CommentContent commentContent = new CommentContent(text);

        // Then
        assertEquals(text, commentContent.getContentText());
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "닭", "소", "뷁"} )
    void givenContentLengthThisOver_whenCreatedCommentContent_thenThrowError(String word) {
        // Given
        String content = word.repeat(101);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreatedCommentContent_thenThrowError(String value) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(value));
    }

    @Test
    void givenContentLengthIsOk_whenUpdated_thenReturnUpdatedContent() {
        // Given
        String content = "this is a test";
        CommentContent commentContent = new CommentContent(content);

        // When
        commentContent.updateContent(content);

        // Then
        assertEquals(content, commentContent.getContentText());
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "닭", "소", "뷁"} )
    void givenContentLengthIsOver_whenUpdated_thenThrowError(String value) {
        // Given
        String content = "this is a test";
        CommentContent commentContent = new CommentContent(content);

        // When & Then
        String updateContent = value.repeat(101);
        assertThrows(IllegalArgumentException.class, () -> commentContent.updateContent(updateContent));
    }
    
}
