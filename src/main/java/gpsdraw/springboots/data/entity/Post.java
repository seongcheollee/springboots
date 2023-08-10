package gpsdraw.springboots.data.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_id;

    //@Column(columnDefinition = "TEXT")
    //private String photo;

    @Column(length = 500)
    private String post_content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime post_datetime;

    @ManyToOne
    @JoinColumn(name = "uid") // 외래키 컬럼 이름 지정
    private User user;


    //  private Integer likes;

 //   private String category;

  //  private Double location_x;

//    private Double location_y;
/*
    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

 */
}
