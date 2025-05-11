package org.maximum0.post.repository;

import lombok.RequiredArgsConstructor;
import org.maximum0.post.aplication.interfaces.LikeRepository;
import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.comment.Comment;
import org.maximum0.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {
    @Override
    public boolean checkedLike(Post post, User user) {
        return false;
    }

    @Override
    public void like(Post post, User user) {

    }

    @Override
    public void unlike(Post post, User user) {

    }

    @Override
    public boolean checkedLike(Comment comment, User user) {
        return false;
    }

    @Override
    public void like(Comment comment, User user) {

    }

    @Override
    public void unlike(Comment comment, User user) {

    }
}
