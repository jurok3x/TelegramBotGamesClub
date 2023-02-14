package com.ykotsiuba.soloveibot.parser.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.ykotsiuba.soloveibot.entity.Command;
import com.ykotsiuba.soloveibot.entity.CommandType;
import com.ykotsiuba.soloveibot.parser.CommandParser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Optional;

class BaseCommandParserTest {
    
    private CommandParser parser;

    @BeforeEach
    void setUp() throws Exception {
        parser = new BaseCommandParser();
        Field username = BaseCommandParser.class.getDeclaredField("username");
        username.setAccessible(true);
        username.set(parser, "roman_solovei_bot");
    }

    @Test
    void parseWheatherCommandTest() {
        assertEquals(Optional.of(prepareCommand()), parser.parse("/weather@roman_solovei_bot"));
    }
    
    private Command prepareCommand() {
        return Command.builder()
                .command("")
                .type(CommandType.CURRENT_WEATHER)
                .build();
    }

}
