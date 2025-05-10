package org.maximum0.post.domain.common;

import java.time.LocalDateTime;

public class DateTime {
    private boolean isEdited;
    private LocalDateTime datetime;

    public DateTime() {
        this.isEdited = false;
        this.datetime = LocalDateTime.now();
    }

    public void updatedEditDatetime() {
        this.isEdited = true;
        this.datetime = LocalDateTime.now();
    }

    public boolean isEdited() {
        return isEdited;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }
}
