package org.maximum0.post.domain.content;

import org.maximum0.post.domain.common.DateTime;

public abstract class Content {
    private String contentText;
    private final DateTime datetime;

    public Content(String contentText) {
        checkedText(contentText);
        this.contentText = contentText;
        this.datetime = new DateTime();
    }

    public void updateContent(String updateContent) {
        checkedText(updateContent);
        this.contentText = updateContent;
        this.datetime.updatedEditDatetime();
    }

    protected abstract void checkedText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
