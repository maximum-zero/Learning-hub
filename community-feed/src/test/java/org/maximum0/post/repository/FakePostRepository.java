package org.maximum0.post.repository;

import java.util.HashMap;
import java.util.Map;
import org.maximum0.post.aplication.interfaces.PostRepository;
import org.maximum0.post.domain.Post;

public class FakePostRepository implements PostRepository {
    private final Map<Long, Post> store = new HashMap<>();

    @Override
    public Post save(Post post) {
        if (post.getId() != null) {
            store.put(post.getId(), post);
            return post;
        }

        long id = store.size() + 1;
        Post newPost = Post.createPost(id, post.getAuthor(), post.getContent());
        store.put(id, newPost);
        return newPost;
    }

    @Override
    public Post findById(Long id) {
        return store.get(id);
    }
}
