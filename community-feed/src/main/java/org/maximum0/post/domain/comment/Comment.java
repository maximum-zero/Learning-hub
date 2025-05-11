package org.maximum0.post.domain.comment;

import org.maximum0.common.domain.PositiveCounter;
import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.content.CommentContent;
import org.maximum0.post.domain.content.Content;
import org.maximum0.user.domain.User;

public class Comment {
    private final Long id;
    private final Post post;
    private final User author;
    private final CommentContent content;
    private final PositiveCounter likeCount;

    public Comment(Long id, Post post, User author, CommentContent content) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        if (post == null) {
            throw new IllegalArgumentException();
        }

        if (content == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveCounter();
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        likeCount.increase();
    }

    public void unlike(User user) {
        likeCount.decrease();
    }

    public void updateComment(User user, String updateContent) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.content.updateContent(updateContent);
    }

    public String getContent() {
        return content.getContentText();
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

}
