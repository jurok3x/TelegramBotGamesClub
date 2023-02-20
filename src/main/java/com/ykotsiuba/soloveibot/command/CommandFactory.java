package com.ykotsiuba.soloveibot.command;

import com.ykotsiuba.soloveibot.entity.CommandType;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CommandFactory {
    
    private static final String HANDLER_NAME_SUFFIX = "CommandHandler";
    private BeanFactory beanFactory;
    
    public CommandHandler getCommandHandler(CommandType type) {
        return beanFactory.getBean(extractCommand(type) + HANDLER_NAME_SUFFIX, CommandHandler.class);
    }
    
    private String extractCommand(CommandType type) {
        if(type.getName().contains("12h")) {
            return "Weather12HForecast";
        }
        if(type.getName().contains("5d")) {
            return "Weather5DForecast";
        }
        if(type.getName().contains("weather")) {
            return "Weather";
        }
        return null;
    }

}
