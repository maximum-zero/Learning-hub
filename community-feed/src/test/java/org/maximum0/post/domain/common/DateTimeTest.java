package org.maximum0.post.domain.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DateTimeTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStateArsUpdated() throws InterruptedException {
        // Given
        DateTime dateTime = new DateTime();
        LocalDateTime localDateTime = dateTime.getDatetime();

        // When
        Thread.sleep(1);
        dateTime.updatedEditDatetime();

        // Then
        assertTrue(dateTime.isEdited());
        assertNotEquals(localDateTime, dateTime.getDatetime());
    }

}
