package com.ykotsiuba.soloveibot.command;

import com.ykotsiuba.soloveibot.entity.CommandType;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface CommandHandler {
    
    void handleCommand(Message message);
    
    CommandType getCommand();

}
