package org.maximum0.user.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.maximum0.user.application.dto.CreateUserRequestDto;
import org.maximum0.user.application.dto.FollowUserRequestDto;
import org.maximum0.user.application.interfaces.UserRelationRepository;
import org.maximum0.user.application.interfaces.UserRepository;
import org.maximum0.user.domain.User;
import org.maximum0.user.repository.FakeUserRelationRepository;
import org.maximum0.user.repository.FakeUserRepository;

class UserRelationServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

    private User user1;
    private User user2;

    private FollowUserRequestDto request;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("user", "");
        user1 = userService.createUser(dto);
        user2 = userService.createUser(dto);

        request = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
        // When
        userRelationService.follow(request);

        // Then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());

        assertEquals(0, user2.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
    }

    @Test
    void givenCreateTwoUserFollow_whenFollow_thenUserThrowError() {
        // Given
        userRelationService.follow(request);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(request));
    }


    @Test
    void givenCreateOneUser_whenFollowBySelf_thenUserThrowError() {
        // Given
        FollowUserRequestDto sameUser = request = new FollowUserRequestDto(user1.getId(), user1.getId());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
    }

    @Test
    void givenCreateTwoUser_whenUnfollow_thenUserUnfollowSaved() {
        // Given
        userRelationService.follow(request);

        // When
        userRelationService.unfollow(request);

        // Then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());

        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

    @Test
    void givenCreateTwoUserFollow_whenUnfollow_thenUserThrowError() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(request));
    }

    @Test
    void givenCreateOneUser_whenUnfollowBySelf_thenUserThrowError() {
        // Given
        FollowUserRequestDto sameUser = request = new FollowUserRequestDto(user1.getId(), user1.getId());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(sameUser));
    }

}
