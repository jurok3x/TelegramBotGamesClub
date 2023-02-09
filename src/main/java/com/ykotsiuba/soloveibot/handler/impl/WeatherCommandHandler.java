package com.ykotsiuba.soloveibot.handler.impl;

import com.ykotsiuba.soloveibot.entity.Command;
import com.ykotsiuba.soloveibot.handler.CommandHandler;
import com.ykotsiuba.soloveibot.telegrambots.SoloveiTelegramBot;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class WeatherCommandHandler implements CommandHandler {
    
    private final SoloveiTelegramBot bot;

    @Override
    public void handleCommand(Message message) {
        // TODO Auto-generated method stub

    }

    @Override
    public Command getCommand() {
        return Command.CURRENT_WEATHER;
    }

}
