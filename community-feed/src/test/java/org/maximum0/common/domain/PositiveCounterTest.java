package org.maximum0.common.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PositiveCounterTest {

    @Test
    void givenCreated_whenIncrease_thenCountIsOne() {
        // Given
        PositiveCounter counter = new PositiveCounter();

        // When
        counter.increase();

        // Then
        assertEquals(1, counter.getCount());
    }

    @Test
    void givenCreatedAndIncrease_whenDecrease_thenCountIsZero() {
        // Given
        PositiveCounter counter = new PositiveCounter();
        counter.increase();

        // When
        counter.decrease();

        // Then
        assertEquals(0, counter.getCount());
    }


    @Test
    void givenCreated_whenDecrease_thenCountIsZero() {
        // Given
        PositiveCounter counter = new PositiveCounter();

        // When
        counter.decrease();

        // Then
        assertEquals(0, counter.getCount());
    }

}
