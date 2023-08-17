package gpsdraw.springboots.data.repository;


import gpsdraw.springboots.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserUid(String userUid);

    @Query("SELECT p FROM Post p WHERE p.post_id = :postId AND p.user.uid = :userUid")
    Optional<Post> findByPost_idAndUser_Uid(@Param("postId") Integer postId, @Param("userUid") String userUid);


    Post updatePostByPost_idAndUser_Uid();
}