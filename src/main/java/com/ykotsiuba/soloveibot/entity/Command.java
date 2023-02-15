package com.ykotsiuba.soloveibot.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Command {
    
    private CommandType type;
    private String commandText;

}
