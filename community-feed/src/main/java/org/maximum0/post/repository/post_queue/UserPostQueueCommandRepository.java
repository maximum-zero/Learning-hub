package org.maximum0.post.repository.post_queue;

import org.maximum0.post.repository.entity.post.PostEntity;

public interface UserPostQueueCommandRepository {

    void publishPost(PostEntity postEntity);
    void saveFollowPost(Long userId, Long targetId);
    void deleteUnfollowPost(Long userId, Long targetId);

}
