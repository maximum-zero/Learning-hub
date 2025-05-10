package org.maximum0.user.domain;

import java.util.Objects;
import org.maximum0.common.domain.PositiveCounter;

public class User {
    private final Long id;
    private final UserInfo userInfo;
    private final PositiveCounter followingCount;
    private final PositiveCounter followerCount;

    public User(Long id, UserInfo userInfo) {
        this.id = id;
        this.userInfo = userInfo;
        this.followerCount = new PositiveCounter();
        this.followingCount = new PositiveCounter();
    }

    public void follow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        followingCount.increase();
        increaseFollowerCount();
    }

    public void unFollow(User targetUser) {
        if (this.equals(targetUser)) {
            throw new IllegalArgumentException();
        }
        followingCount.decrease();
        decreaseFollowerCount();
    }

    // 디미터의 법칙 - 자신의 소유 객체랑만 이야기 하는 것이 좋음 (캡슐화가 깨질 수 있음)
    private void increaseFollowerCount() {
        followingCount.increase();
    }

    private void decreaseFollowerCount() {
        followingCount.decrease();
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
