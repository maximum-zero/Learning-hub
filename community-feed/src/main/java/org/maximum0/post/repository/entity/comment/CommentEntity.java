package org.maximum0.post.repository.entity.comment;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.maximum0.common.domain.PositiveCounter;
import org.maximum0.common.repository.entity.TimeBaseEntity;
import org.maximum0.post.domain.comment.Comment;
import org.maximum0.post.domain.content.CommentContent;
import org.maximum0.post.repository.entity.post.PostEntity;
import org.maximum0.user.repository.entity.UserEntity;

@Entity
@Table(name = "tb_comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;


    @ManyToOne
    @JoinColumn(name="post_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PostEntity post;

    private String content;
    private Integer likeCount;

    public CommentEntity(Comment comment) {
        this.id = comment.getId();
        this.post = new PostEntity(comment.getPost());
        this.author = new UserEntity(comment.getAuthor());
        this.content = comment.getContent();
        this.likeCount = comment.getLikeCount();
    }

    public Comment toComment() {
        return Comment.builder()
                .id(id)
                .post(post.toPost())
                .author(author.toUser())
                .content(new CommentContent(content))
                .likeCount(new PositiveCounter(likeCount))
                .build();
    }

}
