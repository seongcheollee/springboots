package gpsdraw.springboots.domain;


import lombok.Data;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "routes")
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Getter와 Setter 메서드
}
