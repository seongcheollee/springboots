package gpsdraw.springboots.domain;

import lombok.Data;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "follows")
@Data

public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "following_user_id")
    private User followingUser;

    @ManyToOne
    @JoinColumn(name = "followed_user_id")
    private User followedUser;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Getters and setters
}