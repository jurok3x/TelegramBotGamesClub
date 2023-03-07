package com.ykotsiuba.soloveibot.service;

import com.ykotsiuba.soloveibot.entity.dto.TelegramMessageDto;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageService {
    
    void sendToTelegramChat(String message);
    
    TelegramMessageDto save(Message message);

}
