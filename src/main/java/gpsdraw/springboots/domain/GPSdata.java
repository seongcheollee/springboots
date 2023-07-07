package gpsdraw.springboots.domain;

import lombok.Data;




import javax.persistence.*;
import java.math.BigDecimal;
import java.security.Timestamp;

@Entity
@Data
@Table(name = "gps_data")
public class GPSdata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(name = "coordinates")
    private BigDecimal coordinates;

    @Column(name = "created_at")
    private Timestamp createdAt;

    // Getter와 Setter 메서드
}
