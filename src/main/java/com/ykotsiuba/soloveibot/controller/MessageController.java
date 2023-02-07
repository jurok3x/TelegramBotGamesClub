package com.ykotsiuba.soloveibot.controller;

import com.ykotsiuba.soloveibot.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/messages" )
@AllArgsConstructor
public class MessageController {

    private MessageService messageService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        messageService.sendMessage(message);
        return message;
    }
}
