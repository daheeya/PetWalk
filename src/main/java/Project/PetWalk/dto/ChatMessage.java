package Project.PetWalk.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChatMessage {
    private String content;
    private String sender;
    private String room;
}