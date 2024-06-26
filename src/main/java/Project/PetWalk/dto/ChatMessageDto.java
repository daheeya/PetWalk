package Project.PetWalk.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type; // 메시지 타입
    private String roomId; // 방 번호
    private Long sender; // 채팅을 보낸 사람
    private String message; // 메시지
    private String time;
}