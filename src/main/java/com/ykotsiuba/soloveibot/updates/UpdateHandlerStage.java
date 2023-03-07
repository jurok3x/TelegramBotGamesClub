package com.ykotsiuba.soloveibot.updates;

public enum UpdateHandlerStage {
    
    COMMAND,
    MESSAGE,
    POOL,
    POOL_ANSWER;
    
    public int getOrder() {
        return ordinal();
    }

}
