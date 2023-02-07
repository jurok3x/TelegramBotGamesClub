package com.ykotsiuba.soloveibot.telegrambots;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class SoloveiTelegramBot extends TelegramLongPollingBot{
    
    @Value("${bot.token}")
    private String token;
    @Value("${bot.username}")
    private String username;

    @Override
    public void onUpdateReceived(Update update) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

}
