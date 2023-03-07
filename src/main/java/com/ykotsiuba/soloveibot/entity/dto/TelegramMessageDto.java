package com.ykotsiuba.soloveibot.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelegramMessageDto {
    private Integer id;
    private String text;
    private String user;
    private Date date;
    private Long chatId;
}
