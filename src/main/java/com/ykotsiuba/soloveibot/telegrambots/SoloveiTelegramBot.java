package com.ykotsiuba.soloveibot.telegrambots;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SoloveiTelegramBot extends TelegramLongPollingBot{
    
    @Value("${bot.username}")
    private String token;
    @Value("${bot.token}")
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
