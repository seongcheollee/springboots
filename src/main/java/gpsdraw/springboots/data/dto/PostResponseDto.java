package gpsdraw.springboots.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.stream.events.Comment;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostResponseDto {

    private String user_id;

    private String post_content;

    //private Double location_x;

    //private Double location_y;
}
