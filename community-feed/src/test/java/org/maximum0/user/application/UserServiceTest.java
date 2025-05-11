package org.maximum0.user.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.maximum0.user.application.dto.CreateUserRequestDto;
import org.maximum0.user.application.interfaces.UserRepository;
import org.maximum0.user.domain.User;
import org.maximum0.user.domain.UserInfo;
import org.maximum0.user.repository.FakeUserRepository;

class UserServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        // Given
        CreateUserRequestDto request = new CreateUserRequestDto("user01", "");

        // When
        User savedUser = userService.createUser(request);

        // Then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getUserInfo();
        assertEquals(savedUser.getId(), foundUser.getId());
        assertEquals("user01",  userInfo.getName());
    }

}
