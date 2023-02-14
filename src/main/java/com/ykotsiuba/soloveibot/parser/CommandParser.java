package com.ykotsiuba.soloveibot.parser;

import com.ykotsiuba.soloveibot.entity.Command;

import java.util.Optional;

public interface CommandParser {
    
    Optional<Command> parse(String message);

}
