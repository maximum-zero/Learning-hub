package org.maximum0.post.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.maximum0.common.ui.Response;
import org.maximum0.post.repository.post_queue.UserPostQueueQueryRepository;
import org.maximum0.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository queueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@PathVariable(name = "userId") Long userId, Long lastPostId) {
        List<GetPostContentResponseDto> result = queueQueryRepository.getContentResponse(userId, lastPostId);
        return Response.ok(result);
    }

}
