package com.ykotsiuba.soloveibot.controller;

import com.ykotsiuba.soloveibot.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/messages" )
@AllArgsConstructor
public class MessageController {

    private MessageService messageService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        messageService.sendToTelegramChat(message);
        return message;
    }
}
