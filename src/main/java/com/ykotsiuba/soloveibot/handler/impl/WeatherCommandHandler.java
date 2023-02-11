package com.ykotsiuba.soloveibot.handler.impl;

import com.ykotsiuba.soloveibot.client.impl.OpenWeatherSpringClientService;
import com.ykotsiuba.soloveibot.entity.Command;
import com.ykotsiuba.soloveibot.entity.dto.WeatherResponseDto;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;
import com.ykotsiuba.soloveibot.handler.CommandHandler;
import com.ykotsiuba.soloveibot.mapper.OpenWeatherResponseMapper;
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
    private final OpenWeatherSpringClientService weatherAPI;

    @Override
    public void handleCommand(Message message) {
        OpenWeatherResponse weatherResponse = weatherAPI.getWeatherResponse();
        SendMessage messageRequest = SendMessage.builder()
                .chatId(message.getChatId())
                .text(weatherService.sendReport(OpenWeatherResponseMapper.toResponseDto(weatherResponse)))
                .build();
        try {
            bot.execute(messageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Command getCommand() {
        return Command.CURRENT_WEATHER;
    }

}
