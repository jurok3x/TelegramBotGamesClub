package com.ykotsiuba.soloveibot.telegrambots;

import com.ykotsiuba.soloveibot.updates.UpdatesHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SoloveiTelegramBot extends TelegramLongPollingBot{
    
    @Value("${bot.token}")
    private String token;
    @Value("${bot.username}")
    private String username;
    private final List<UpdatesHandler> handlers;

    @PostConstruct
    private void init() {
       // handlers.stream().sorted(Comparator.comparingInt(UpdatesHandler::getStage));
    }

    @Override
    public void onUpdateReceived(Update update) {
        for(UpdatesHandler handler: handlers){
            if(handler.handleUpdate(update)){
                return;
            }
        }
        
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
