package com.ykotsiuba.soloveibot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/messages/")
public class MessageController {

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        return message;
    }
}
