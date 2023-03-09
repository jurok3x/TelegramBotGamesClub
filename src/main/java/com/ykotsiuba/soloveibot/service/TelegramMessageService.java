package com.ykotsiuba.soloveibot.service;

import com.ykotsiuba.soloveibot.entity.dto.TelegramMessageDto;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface TelegramMessageService {

    TelegramMessageDto save(Message message);
}
