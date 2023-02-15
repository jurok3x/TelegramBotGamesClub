package com.ykotsiuba.soloveibot.updates;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdatesHandler {
    
    boolean handleUpdate(Update update);
    
    UpdateHandlerStage getStage();

}
