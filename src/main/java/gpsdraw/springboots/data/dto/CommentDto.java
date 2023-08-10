package gpsdraw.springboots.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CommentDto {
    private String user_id;

    private String comment_content;
}
