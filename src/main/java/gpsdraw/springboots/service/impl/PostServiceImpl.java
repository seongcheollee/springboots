package gpsdraw.springboots.service.impl;

import gpsdraw.springboots.data.dto.PostDto;
import gpsdraw.springboots.data.dto.PostResponseDto;
import gpsdraw.springboots.data.dto.ProductDto;
import gpsdraw.springboots.data.dto.ProductResponseDto;
import gpsdraw.springboots.data.entity.Post;
import gpsdraw.springboots.data.entity.Product;
import gpsdraw.springboots.data.entity.User;
import gpsdraw.springboots.data.repository.PostRepository;
import gpsdraw.springboots.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; // 추가: Autowired 임포트
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final PostRepository postRepository;

    @Autowired // 추가: Autowired를 통한 의존성 주입
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(post -> new PostResponseDto(
                        post.getUser().getUid(),
                        post.getPost_content()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDto> getPostsByUser(String uid) {
        List<Post> posts = postRepository.findByUserUid(uid);

        return posts.stream()
                .map(post -> new PostResponseDto(
                        post.getUser().getUid(),
                        post.getPost_content()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PostResponseDto> getPostByUserAndPost(String userUid, Integer postId) {
        Optional<Post> posts = postRepository.findByPost_idAndUser_Uid(postId,userUid);

        return posts.map(post -> new PostResponseDto(
                        post.getUser().getUid(),
                        post.getPost_content()
                ));
    }

    @Override
    public PostResponseDto savePost(PostDto postDto, User user) {
        LOGGER.info("[savePost] productName : {}", user.getName());

        Post post = new Post();
        post.setUser(user);
        post.setPost_content(postDto.getPost_content());
        post.setPost_datetime(LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        LOGGER.info("[savePost] saved PostId : {}", savedPost.getPost_id());

        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setUser_id(savedPost.getUser().getUid());
        postResponseDto.setPost_content(savedPost.getPost_content());

        return postResponseDto;
    }

}
