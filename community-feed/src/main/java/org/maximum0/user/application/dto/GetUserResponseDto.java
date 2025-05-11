package org.maximum0.user.application.dto;

import org.maximum0.user.domain.User;

public record GetUserResponseDto(String name, String profileImageUrl, Integer followingCount, Integer followerCount) {

    public GetUserResponseDto(User user) {
        this(user.getName(), user.getProfileImage(), user.getFollowingCount(), user.getFollowerCount());
    }
}
