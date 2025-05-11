package org.maximum0.post.aplication.interfaces;

import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.comment.Comment;
import org.maximum0.user.domain.User;

public interface LikeRepository {
    boolean checkedLike(Post post, User user);
    void like(Post post, User user);
    void unlike(Post post, User user);
    boolean checkedLike(Comment comment, User user);
    void like(Comment comment, User user);
    void unlike(Comment comment, User user);
}
