package org.maximum0.post.repository.post_queue;

import java.util.List;
import org.maximum0.post.ui.dto.GetPostContentResponseDto;

public interface UserPostQueueQueryRepository {

    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId);

}
