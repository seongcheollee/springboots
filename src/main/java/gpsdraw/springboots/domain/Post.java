package gpsdraw.springboots.domain;


import lombok.Data;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private Integer like;

    @Column(name = "created_at")
    private Timestamp createdAt;

    // Getters and setters
}