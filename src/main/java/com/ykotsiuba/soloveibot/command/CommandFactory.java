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
        return beanFactory.getBean(type.getHandler() + HANDLER_NAME_SUFFIX, CommandHandler.class);
    }

}
