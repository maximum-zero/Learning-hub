package org.maximum0.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    @Test
    void givenContentLengthThisOk_whenCreatedPostContent_thenReturnTextContent() {
        // Given
        String text = "this is a test";

        // When
        PostContent postContent = new PostContent(text);

        // Then
        assertEquals(text, postContent.getContentText());
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "닭", "소", "뷁"} )
    void givenContentLengthThisOver_whenCreatedPostContent_thenThrowError(String word) {
        // Given
        String content = word.repeat(501);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "abc", "닭닭닭닭", "a1b1", "A4B4", "!@#$"} )
    void givenContentLengthThisUnder_whenCreatedPostContent_thenThrowError(String value) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreatedPostContent_thenThrowError(String value) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }

    @Test
    void givenContentLengthIsOk_whenUpdated_thenReturnUpdatedContent() {
        // Given
        String content = "this is a test";
        PostContent postContent = new PostContent(content);

        // When
        postContent.updateContent(content);

        // Then
        assertEquals(content, postContent.getContentText());
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "닭", "소", "뷁"} )
    void givenContentLengthIsOver_whenUpdated_thenThrowError(String value) {
        // Given
        String content = "this is a test";
        PostContent postContent = new PostContent(content);

        // When & Then
        String updateContent = value.repeat(501);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(updateContent));
    }


    @ParameterizedTest
    @ValueSource(strings = {"A", "abc", "닭닭닭닭", "a1b1", "A4B4", "!@#$"} )
    void givenContentLengthIsUnder_whenUpdated_thenThrowError(String value) {
        // Given
        String content = "this is a test";
        PostContent postContent = new PostContent(content);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(value));
    }

}
