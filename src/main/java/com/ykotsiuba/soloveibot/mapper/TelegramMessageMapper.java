package com.ykotsiuba.soloveibot.mapper;

import com.ykotsiuba.soloveibot.entity.TelegramMessage;
import com.ykotsiuba.soloveibot.entity.dto.TelegramMessageDto;
import org.springframework.stereotype.Component;

@Component
public class TelegramMessageMapper {

    public static TelegramMessageDto toMessageDto(TelegramMessage message) {
        return TelegramMessageDto.builder()
                .id(message.getId())
                .user(message.getUser())
                .text(message.getText())
                .date(message.getDate())
                .chatId(message.getChatId())
                .build();
    }

    public static TelegramMessage toEntity(TelegramMessageDto messageDto) {
        return TelegramMessage.builder()
                .id(messageDto.getId())
                .user(messageDto.getUser())
                .text(messageDto.getText())
                .date(messageDto.getDate())
                .chatId(messageDto.getChatId())
                .build();
    }
}
