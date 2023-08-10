package gpsdraw.springboots.data.repository;

import gpsdraw.springboots.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
