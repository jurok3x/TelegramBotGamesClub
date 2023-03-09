package com.ykotsiuba.soloveibot.service.impl;

import com.ykotsiuba.soloveibot.dao.TelegramMessageDao;
import com.ykotsiuba.soloveibot.entity.TelegramMessage;
import com.ykotsiuba.soloveibot.entity.dto.TelegramMessageDto;
import com.ykotsiuba.soloveibot.mapper.TelegramMessageMapper;
import com.ykotsiuba.soloveibot.service.TelegramMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.*;
import java.util.Date;

@Service
@AllArgsConstructor
public class TelegramMessageServiceImpl implements TelegramMessageService {

    private TelegramMessageDao messageDao;

    @Override
    public TelegramMessageDto save(Message message) {
        TelegramMessage tm = TelegramMessage.builder()
                .id(message.getMessageId())
                .text(message.getText())
                .user(message.getFrom().getUserName())
                .date(prepareDate(message.getDate()))
                .chatId(message.getChatId())
                .build();
        return TelegramMessageMapper.toMessageDto(messageDao.save(tm));
    }

    private Date prepareDate(int dateInt) {
        Instant instant = Instant.ofEpochSecond(dateInt);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Europe/Kiev"));
        return Date.from(zonedDateTime.toInstant());
    }
}
