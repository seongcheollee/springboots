package gpsdraw.springboots.controller;


import gpsdraw.springboots.data.dto.PostDto;
import gpsdraw.springboots.data.dto.PostResponseDto;
import gpsdraw.springboots.data.entity.User;
import gpsdraw.springboots.data.repository.UserRepository;
import gpsdraw.springboots.service.PostService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final PostService postService;
    private final UserRepository userRepository;

    @Value("${springboot.jwt.secret}")
    private String secretKey = "secretKey";
    @Autowired
    public PostController(PostService postService, UserRepository userRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/all")
    public ResponseEntity<List<PostResponseDto>> getAllPost() {
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[getAllPost] request Data");
        List<PostResponseDto> allPosts = postService.getAllPosts();

        LOGGER.info("[getAllPost] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(allPosts);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{userId}")
    public ResponseEntity<List<PostResponseDto>> getUserPost(@PathVariable String userId) {
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[getUserPost] request Data");

        List<PostResponseDto> allPosts = postService.getPostsByUser(userId);

        LOGGER.info("[getUserPost] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(allPosts);
    }
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{userId}/{postId}")
    public ResponseEntity<Optional<PostResponseDto>> getUserPost(@PathVariable String userId, @PathVariable Integer postId) {
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[getUserPost] request Data");
        Optional<PostResponseDto> allPosts = postService.getPostByUserAndPost(userId,postId);

        LOGGER.info("[getUserPost] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(allPosts);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<PostResponseDto> createProduct(@RequestBody PostDto postDto,
                                                         @RequestHeader("Authorization") String authorizationHeader) {
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[postPost] request Data");

        String jwtToken = authorizationHeader.replace("Bearer ", "");
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken);
        String jwtUid = claims.getBody().getSubject(); // JWT 토큰에서 추출한 UID

        if (jwtUid == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userRepository.getByUid(jwtUid);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        PostResponseDto postResponseDto = postService.savePost(postDto, user);

        LOGGER.info("[createProduct] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }


}
