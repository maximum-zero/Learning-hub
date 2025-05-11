package org.maximum0.post.repository.post_queue;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.maximum0.post.repository.entity.like.QLikeEntity;
import org.maximum0.post.repository.entity.post.QPostEntity;
import org.maximum0.post.repository.entity.post.QUserPostQueueEntity;
import org.maximum0.post.ui.dto.GetPostContentResponseDto;
import org.maximum0.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserPostQueueEntity userPostQueryEntity = QUserPostQueueEntity.userPostQueueEntity;
    private static final QPostEntity postEntity = QPostEntity.postEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QLikeEntity likeEntity = QLikeEntity.likeEntity;

    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastContentId) {
        return queryFactory
                .select(
                        Projections.fields(
                                GetPostContentResponseDto.class,
                                postEntity.id.as("id"),
                                postEntity.content.as("content"),
                                userEntity.id.as("userId"),
                                userEntity.name.as("userName"),
                                userEntity.profileImage.as("userProfileImage"),
                                postEntity.createDt.as("createdAt"),
                                postEntity.updateDt.as("updatedAt"),
                                postEntity.commentCount.as("commentCount"),
                                postEntity.likeCount.as("likeCount"),
                                likeEntity.isNotNull().as("isLikedByMe")
                        )
                )
                .from(userPostQueryEntity)
                .join(postEntity).on(userPostQueryEntity.postId.eq(postEntity.id))
                .join(userEntity).on(userPostQueryEntity.authorId.eq(userEntity.id))
                .leftJoin(likeEntity).on(hasLike(userId))
                .where(
                        userPostQueryEntity.userId.eq(userId),
                        hasLastData(lastContentId)
                )
                .orderBy(userPostQueryEntity.postId.desc())
                .limit(20)
                .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }
        return postEntity.id.lt(lastId);
    }

    private BooleanExpression hasLike(Long userId) {
        if (userId == null) {
            return null;
        }

        return postEntity.id
                .eq(likeEntity.id.targetId)
                .and(likeEntity.id.targetType.eq("POST"))
                .and(likeEntity.id.userId.eq(userId));
    }

}
