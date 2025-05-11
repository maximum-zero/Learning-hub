package org.maximum0.user.application;

import org.maximum0.user.application.dto.FollowUserRequestDto;
import org.maximum0.user.application.interfaces.UserRelationRepository;
import org.maximum0.user.domain.User;

public class UserRelationService {
    private final UserService userService;
    private final UserRelationRepository userRelationRepository;

    public UserRelationService(UserService userService,
            UserRelationRepository userRelationRepository) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(FollowUserRequestDto request) {
        User user = userService.getUser(request.userId());
        User targetUser = userService.getUser(request.targetUserId());

        if (userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException();
        }

        user.follow(targetUser);
        userRelationRepository.save(user, targetUser);
    }

    public void unfollow(FollowUserRequestDto request) {
        User user = userService.getUser(request.userId());
        User targetUser = userService.getUser(request.targetUserId());

        if (!userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException();
        }

        user.unFollow(targetUser);
        userRelationRepository.delete(user, targetUser);
    }

}
