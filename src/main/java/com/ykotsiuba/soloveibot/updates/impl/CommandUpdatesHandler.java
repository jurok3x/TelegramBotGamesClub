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
        if (isCommand(update)) {
            Optional<Command> command = extractCommand(update);
            if (!command.isPresent()) {
                return false;
            }
            executeCommand(command.get(), update.getMessage());
            return true;
        }
        return false;
    }
    
    private boolean isCommand(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().hasText();
        }
        return false;
    }
    
    private void executeCommand(Command command, Message message) {
        CommandHandler handler = factory.getCommandHandler(command.getType());
        handler.handleCommand(message, command.getCommandText());
    }
    
    private Optional<Command> extractCommand(Update update) {
        Message message = update.getMessage();
        String text = message.getText();
        return parser.parse(text);
    }

    @Override
    public UpdateHandlerStage getStage() {
        return UpdateHandlerStage.COMMAND;
    }

}
