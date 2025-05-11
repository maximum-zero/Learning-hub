package org.maximum0.user.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.maximum0.common.domain.PositiveCounter;
import org.maximum0.common.repository.entity.TimeBaseEntity;
import org.maximum0.user.domain.User;
import org.maximum0.user.domain.UserInfo;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.profileImage = user.getProfileImage();
        this.followerCount = user.getFollowerCount();
        this.followingCount = user.getFollowingCount();
    }


    public User toUser() {
        return User.builder()
                .id(id)
                .userInfo(new UserInfo(name, profileImage))
                .followerCount(new PositiveCounter(followerCount))
                .followingCount(new PositiveCounter(followingCount))
                .build();
    }
}
