package com.ykotsiuba.soloveibot.dao.extractor;

import com.ykotsiuba.soloveibot.entity.TelegramMessage;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TelegramMessageExtractor implements RowMapper<TelegramMessage> {
    @Override
    public TelegramMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TelegramMessage.builder()
                .id(rs.getInt("id"))
                .user(rs.getString("user"))
                .text(rs.getString("text"))
                .chatId(rs.getLong("chat_id"))
                .date(rs.getDate("date"))
                .build();
    }
}
