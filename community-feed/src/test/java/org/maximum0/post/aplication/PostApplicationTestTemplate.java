package org.maximum0.post.aplication;

import org.maximum0.fake.FakeObjectFactory;
import org.maximum0.post.aplication.dto.CreateCommentRequestDto;
import org.maximum0.post.aplication.dto.CreatePostRequestDto;
import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.content.PostPublicationState;
import org.maximum0.user.application.UserService;
import org.maximum0.user.application.dto.CreateUserRequestDto;
import org.maximum0.user.domain.User;

public class PostApplicationTestTemplate {
    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.postService();
    final CommentService commentService = FakeObjectFactory.commentService();

    final User user = userService.createUser(new CreateUserRequestDto("user1", ""));
    final User otherUser = userService.createUser(new CreateUserRequestDto("user2", ""));

    final CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);
    final Post post = postService.createPost(postRequestDto);

    final String commentContent = "this is test content";
    final CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), commentContent);

}
