package com.ykotsiuba.soloveibot.command.impl;

import com.ykotsiuba.soloveibot.command.CommandHandler;
import com.ykotsiuba.soloveibot.entity.CommandType;
import com.ykotsiuba.soloveibot.service.impl.FeignWeatherService;
import com.ykotsiuba.soloveibot.telegrambots.SoloveiTelegramBot;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component("CurrentWeatherCommandHandler")
@AllArgsConstructor
public class WeatherCommandHandler implements CommandHandler {
    
    private SoloveiTelegramBot bot;
    private FeignWeatherService weatherService;

    @Override
    public void handleCommand(Message message, String text) {
        SendMessage messageRequest = SendMessage.builder()
                .chatId(message.getChatId())
                .text(weatherService.getCurrentWeatherReport())
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
