package org.maximum0.post.repository.jpa;

import org.maximum0.post.repository.entity.comment.CommentEntity;
import org.maximum0.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
            + "SET c.content = :#{#commentEntity.getContent()}, "
            + "c.updateDt = now() "
            + "WHERE c.id = :#{#commentEntity.getId()} ")
    void updateCommentEntity(CommentEntity commentEntity);

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
            + "SET c.likeCount = c.likeCount + :likeCount, "
            + "c.updateDt = now() "
            + "WHERE c.id = :id ")
    void updateCommentLikeCount(Long id, Integer likeCount);

}
