package com.ykotsiuba.soloveibot.updates;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface UpdatesHandler {
    
    boolean handleUpdate(Update update);
    
    UpdateHandlerStage getStage();

}
