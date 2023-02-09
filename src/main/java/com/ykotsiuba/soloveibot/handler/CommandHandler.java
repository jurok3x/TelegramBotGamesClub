package com.ykotsiuba.soloveibot.handler;

import com.ykotsiuba.soloveibot.entity.Command;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface CommandHandler {
    
    void handleCommand(Message message);
    
    Command getCommand();

}
