package gpsdraw.springboots.data.repository;


import gpsdraw.springboots.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {

}