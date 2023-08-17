package gpsdraw.springboots.service;

import gpsdraw.springboots.data.dto.PostDto;
import gpsdraw.springboots.data.dto.PostResponseDto;
import gpsdraw.springboots.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<PostResponseDto> getAllPosts();
    List<PostResponseDto> getPostsByUser(String uid);
    Optional<PostResponseDto> getPostByUserAndPost(String userUid, Integer postId);
    PostResponseDto savePost(PostDto postDto, User user);
}

