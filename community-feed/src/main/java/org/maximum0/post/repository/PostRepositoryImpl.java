package org.maximum0.post.repository;

import lombok.RequiredArgsConstructor;
import org.maximum0.post.aplication.interfaces.PostRepository;
import org.maximum0.post.domain.Post;
import org.maximum0.post.repository.entity.post.PostEntity;
import org.maximum0.post.repository.jpa.JpaPostRepository;
import org.maximum0.post.repository.post_queue.UserPostQueueCommandRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommandRepository commandRepository;

    @Transactional
    @Override
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);
        if (post.getId() != null) {
            jpaPostRepository.updatePostEntity(postEntity);
            return postEntity.toPost();
        }
        postEntity = jpaPostRepository.save(postEntity);
        commandRepository.publishPost(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow();
        return postEntity.toPost();
    }
}
