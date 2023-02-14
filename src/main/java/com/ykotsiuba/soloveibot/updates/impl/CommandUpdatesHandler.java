package com.ykotsiuba.soloveibot.updates.impl;

import com.ykotsiuba.soloveibot.command.CommandFactory;
import com.ykotsiuba.soloveibot.command.CommandHandler;
import com.ykotsiuba.soloveibot.entity.Command;
import com.ykotsiuba.soloveibot.parser.CommandParser;
import com.ykotsiuba.soloveibot.updates.UpdateHandlerStage;
import com.ykotsiuba.soloveibot.updates.UpdatesHandler;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CommandUpdatesHandler implements UpdatesHandler {
    
    private CommandFactory factory;
    private CommandParser parser;

    @Override
    public boolean handleUpdate(Update update) {
        if (!update.hasMessage()) {
            return false;
          }
          Message message = update.getMessage();
          if (!message.hasText()) {
            return false;
          }
          String text = message.getText();
          Optional<Command> command = parser.parse(text);
          if (!command.isPresent()) {
            return false;
          }
        CommandHandler handler = factory.getHandler(command.get().getType());
          handler.handleCommand(message, command.get().getCommand());
        return true;
    }

    @Override
    public UpdateHandlerStage getStage() {
        return UpdateHandlerStage.COMMAND;
    }

}
