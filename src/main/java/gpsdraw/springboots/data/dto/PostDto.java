package gpsdraw.springboots.data.dto;

import lombok.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PostDto {

    // private String user_id;

    private String post_content;

    //private Double location_x;

    // private Double location_y;

    //  private List<Comment> commentList;
}