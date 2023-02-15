package com.ykotsiuba.soloveibot.updates;

public enum UpdateHandlerStage {
    
    COMMAND;
    
    public int getOrder() {
        return ordinal();
    }

}
