package com.ykotsiuba.soloveibot.command.impl;

import com.ykotsiuba.soloveibot.command.CommandHandler;
import com.ykotsiuba.soloveibot.entity.CommandType;
import com.ykotsiuba.soloveibot.service.WeatherService;
import com.ykotsiuba.soloveibot.telegrambots.SoloveiTelegramBot;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class WeatherCommandHandler implements CommandHandler {
    
    private final SoloveiTelegramBot bot;
    private final WeatherService weatherService;

    @Override
    public void handleCommand(Message message, String text) {
        SendMessage messageRequest = SendMessage.builder()
                .chatId(message.getChatId())
                .text(weatherService.sendReport())
                .build();
        sendMessage(messageRequest);
    }

    private void sendMessage(SendMessage messageRequest) {
        try {
            bot.execute(messageRequest);
        } catch (TelegramApiException e) {
            System.out.println(String.format("Error sending message. Reason: %s", e.getMessage()));
        }
    }

    @Override
    public CommandType getCommand() {
        return CommandType.CURRENT_WEATHER;
    }

}
