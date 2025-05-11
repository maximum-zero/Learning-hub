package org.maximum0.post.repository.jpa;

import java.util.List;
import org.maximum0.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Query(value = "SELECT p.id FROM PostEntity p WHERE p.author.id = :authorId")
    List<Long> findAllPostIdsByAuthorId(Long authorId);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
            + "SET p.content = :#{#postEntity.getContent()}, "
            + "p.state = :#{#postEntity.getState()}, "
            + "p.updateDt = now() "
            + "WHERE p.id = :#{#postEntity.getId()} ")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
            + "SET p.likeCount = :#{#postEntity.getLikeCount()}, "
            + "p.updateDt = now() "
            + "WHERE p.id = :#{#postEntity.getId()} ")
    void updatePostLikeCount(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
            + "SET p.commentCount = p.commentCount + 1, "
            + "p.updateDt = now() "
            + "WHERE p.id = :id ")
    void increaseCommentCount(Long id);

}
