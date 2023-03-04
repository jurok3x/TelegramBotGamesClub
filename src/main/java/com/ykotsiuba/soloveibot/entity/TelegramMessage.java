package com.ykotsiuba.soloveibot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelegramMessage {
    private Integer id;
    private String text;
    private String user;
    private Date date;
    private Long chatId;
}
