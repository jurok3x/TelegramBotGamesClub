package com.ykotsiuba.soloveibot.command;

import com.ykotsiuba.soloveibot.entity.Command;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class CommandFactory {
    
    private final List<CommandHandler> handlers;
    private Map<Command, CommandHandler> handlersMap;
    
    @PostConstruct
    private void init() {
        handlersMap = new HashMap<>();
        handlers.stream().forEach(
                handler -> handlersMap.put(handler.getCommand(), handler));
    }
    
    public CommandHandler getHandler(Command command) {
        return Optional.ofNullable(handlersMap.get(command))
                .orElseThrow(() -> new IllegalStateException("Not supported command: " + command.getCommand()));
    }

}
