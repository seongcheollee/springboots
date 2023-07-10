package gpsdraw.springboots.service;

import gpsdraw.springboots.domain.Post;
import java.util.List;

public interface PostService {
    void createPost(Post post);
    Post getPostById(Integer id);
    List<Post> getAllPosts();
    void updatePost(Post post);
    void deletePost(Integer id);
}
