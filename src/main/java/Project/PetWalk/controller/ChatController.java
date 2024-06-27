package Project.PetWalk.controller;

import Project.PetWalk.dto.ChatMessage;
import Project.PetWalk.dto.ChatRoom;
import Project.PetWalk.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/chat")
    public String mainChat() {
        return "/index";
    }

    @MessageMapping("/chat.sendMessage")
    //@SendTo("/topic/{room}")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoom(), chatMessage);
        //return chatMessage;
    }


    @MessageMapping("/chat.addUser")
    //@SendTo("/topic/{room}")
    public void addUser(@Payload ChatMessage chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoom(), chatMessage);

        //return chatMessage;
    }
}
