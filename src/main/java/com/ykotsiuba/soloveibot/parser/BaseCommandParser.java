package com.ykotsiuba.soloveibot.parser;

import java.util.Optional;

import com.ykotsiuba.soloveibot.entity.Command;

import java.util.AbstractMap.SimpleEntry;

public class BaseCommandParser {
    
    private final String PREFIX_FOR_COMMAND = "/";
    private final String DELIMITER_COMMAND_BOTNAME = "@";
    
    public Optional<ParsedCommandDto> parseCommand(String message) {
        if (StringUtil.isBlank(message)) {
          return Optional.empty();
        }
        String trimText = StringUtil.trim(message);
        SimpleEntry<String, String> commandAndText = getDelimitedCommandFromText(trimText);
        if (isCommand(commandAndText.getKey())) {
          if (isCommandForMe(commandAndText.getKey())) {
            String commandForParse = cutCommandFromFullText(commandAndText.getKey());
            Optional<Command> command = Command.parseCommand(commandForParse);
            return command.map(c -> new ParsedCommandDto(c, commandAndText.getValue()));
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

      private boolean isCommandForMe(String command) {
        if (command.contains(DELIMITER_COMMAND_BOTNAME)) {
          String botNameForEqual = command.substring(command.indexOf(DELIMITER_COMMAND_BOTNAME) + 1);
          return botName.equals(botNameForEqual);
        }
        return true;
      }

      private boolean isCommand(String text) {
        return text.startsWith(PREFIX_FOR_COMMAND);
      }

}
