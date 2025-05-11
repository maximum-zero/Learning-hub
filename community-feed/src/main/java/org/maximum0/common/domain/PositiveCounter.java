package org.maximum0.common.domain;

public class PositiveCounter {
    private int count;

    public PositiveCounter() {
        this.count = 0;
    }

    public void increase() {
        this.count++;
    }

    public void decrease() {
        if (count <= 0) {
            return;
        }
        this.count--;
    }

    public int getCount() {
        return count;
    }
}
