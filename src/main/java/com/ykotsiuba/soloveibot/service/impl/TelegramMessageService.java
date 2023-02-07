package com.ykotsiuba.soloveibot.service.impl;

import com.ykotsiuba.soloveibot.service.MessageService;
import com.ykotsiuba.soloveibot.telegrambots.SoloveiTelegramBot;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@AllArgsConstructor
public class TelegramMessageService implements MessageService {
    
    private SoloveiTelegramBot bot;

    @Override
    public void sendMessage(String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(String.format("Повідомлення від Романа: %s", message));
        sendMessage.setChatId("332187582");
        executeCommand(sendMessage);
    }

    private void executeCommand(SendMessage sendMessage) {
        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(String.format("Error sending message. Reason: %s", e.getMessage()));
        }
    }

}
