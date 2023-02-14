package com.ykotsiuba.soloveibot.updates.impl;

import com.ykotsiuba.soloveibot.command.CommandFactory;
import com.ykotsiuba.soloveibot.parser.CommandParser;
import com.ykotsiuba.soloveibot.updates.UpdateHandlerStage;
import com.ykotsiuba.soloveibot.updates.UpdatesHandler;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@AllArgsConstructor
public class CommandUpdatesHandler implements UpdatesHandler {
    
    private CommandFactory factory;
    private CommandParser parser;

    @Override
    public boolean handleUpdate(Update update) throws TelegramApiException {
        if (!update.hasMessage()) {
            return false;
          }
          Message message = update.getMessage();
          if (!message.hasText()) {
            return false;
          }
        return false;
    }

    @Override
    public UpdateHandlerStage getStage() {
        return UpdateHandlerStage.COMMAND;
    }

}
