package gpsdraw.springboots.service;

import gpsdraw.springboots.data.dto.PostDto;
import gpsdraw.springboots.data.dto.PostResponseDto;
import gpsdraw.springboots.data.dto.ProductDto;
import gpsdraw.springboots.data.dto.ProductResponseDto;
import gpsdraw.springboots.data.entity.Post;
import gpsdraw.springboots.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<PostResponseDto> getAllPosts();

    PostResponseDto savePost(PostDto postDto, User user);

}

