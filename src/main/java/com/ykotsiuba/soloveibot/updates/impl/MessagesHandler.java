package com.ykotsiuba.soloveibot.updates.impl;

import com.ykotsiuba.soloveibot.updates.UpdateHandlerStage;
import com.ykotsiuba.soloveibot.updates.UpdatesHandler;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@AllArgsConstructor
public class MessagesHandler implements UpdatesHandler {

    @Override
    public boolean handleUpdate(Update update) {
        if(!validateMessage(update)) {
            return false;
        }
        return true;
    }
    
    private boolean validateMessage(Update update) {
        if(update.hasMessage()) {
           return update.getMessage().hasText();
        }
        return false;
    }

    @Override
    public UpdateHandlerStage getStage() {
        return UpdateHandlerStage.MESSAGE;
    }

}
