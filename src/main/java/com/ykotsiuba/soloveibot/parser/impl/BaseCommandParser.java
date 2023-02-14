package com.ykotsiuba.soloveibot.parser.impl;

import java.util.Optional;

import com.ykotsiuba.soloveibot.entity.Command;
import com.ykotsiuba.soloveibot.entity.CommandType;
import com.ykotsiuba.soloveibot.parser.CommandParser;
import com.ykotsiuba.soloveibot.util.StringUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Stream;

@Component
public class BaseCommandParser implements CommandParser{
    
    @Value("${bot.username}")
    private String username;
    private final String PREFIX_FOR_COMMAND = "/";
    private final String DELIMITER_COMMAND_BOTNAME = "@";
    
    public Optional<Command> parse(String message) {
        if (StringUtils.isBlank(message)) {
          return Optional.empty();
        }
        String trimText = StringUtils.trim(message);
        SimpleEntry<String, String> commandAndText = getDelimitedCommandFromText(trimText);
        if (isCommand(commandAndText.getKey())) {
          if (isCommandForMe(commandAndText.getKey())) {
            String commandForParse = cutCommandFromFullText(commandAndText.getKey());
            Optional<CommandType> command = parseCommand(commandForParse);
            return command.map(c -> prepareCommand(c, commandAndText.getValue()));
          } else {
            return Optional.empty();
          }
        }
        return Optional.empty();
      }

      private String cutCommandFromFullText(String text) {
        return text.contains(DELIMITER_COMMAND_BOTNAME)
            ? text.substring(1, text.indexOf(DELIMITER_COMMAND_BOTNAME))
            : text.substring(1);
      }

      private SimpleEntry <String, String> getDelimitedCommandFromText(String trimText) {
          SimpleEntry <String, String> commandText;

        if (trimText.contains(" ")) {
          int indexOfSpace = trimText.indexOf(" ");
          commandText =
              new SimpleEntry <>(trimText.substring(0, indexOfSpace), trimText.substring(indexOfSpace + 1));
        } else commandText = new SimpleEntry <>(trimText, "");
        return commandText;
      }
      
      private Optional<CommandType> parseCommand(String command) {
          if (StringUtils.isBlank(command)) {
            return Optional.empty();
          }
          String formatName = StringUtils.trim(command).toLowerCase();
          return Stream.of(CommandType.values()).filter(c -> c.getName().equalsIgnoreCase(formatName)).findFirst();
        }

      private boolean isCommandForMe(String command) {
        if (command.contains(DELIMITER_COMMAND_BOTNAME)) {
          String botNameForEqual = command.substring(command.indexOf(DELIMITER_COMMAND_BOTNAME) + 1);
          return username.equals(botNameForEqual);
        }
        return true;
      }

      private boolean isCommand(String text) {
        return text.startsWith(PREFIX_FOR_COMMAND);
      }
      
      private Command prepareCommand(CommandType type, String command) {
          return Command.builder()
                  .command(command)
                  .type(type)
                  .build();
      }

}
