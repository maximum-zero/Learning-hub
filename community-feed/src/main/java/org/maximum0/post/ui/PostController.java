package org.maximum0.post.ui;

import lombok.RequiredArgsConstructor;
import org.maximum0.common.ui.Response;
import org.maximum0.post.aplication.PostService;
import org.maximum0.post.aplication.dto.CreatePostRequestDto;
import org.maximum0.post.aplication.dto.UpdatePostRequestDto;
import org.maximum0.post.domain.Post;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @RequestMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto requestDto) {
        Post post = postService.createPost(requestDto);
        return Response.ok(post.getId());
    }

    @PutMapping("/{postId}")
    public Response<Long> updatePost(@PathVariable(name = "postId") Long postId, @RequestBody UpdatePostRequestDto requestDto) {
        Post post = postService.updatePost(postId, requestDto);
        return Response.ok(post.getId());
    }

}
