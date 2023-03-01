package com.ykotsiuba.soloveibot.dao.impl;

import com.ykotsiuba.soloveibot.dao.TelegramMessageDao;
import com.ykotsiuba.soloveibot.entity.TelegramMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@PropertySource(value = "classpath:/db/message/message_queries.properties")
@Slf4j
public class TelegramMessageDaoImpl implements TelegramMessageDao {

    private final NamedParameterJdbcTemplate template;
    private final RowMapper<TelegramMessage> rowMapper;
    @Value("${find.by_id}")
    private String findById;
    @Value("${find.all}")
    private String findAll;
    @Value("${find.by_chat_id}")
    private String findByChatId;
    @Value("${delete.by_id}")
    private String deleteById;
    @Value("${save}")
    private String save;
    @Value("${update.text}")
    private String updateText;

    @Override
    public Optional<TelegramMessage> getMessageById(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        TelegramMessage message = null;
        try {
            message = template.queryForObject(findById, param, BeanPropertyRowMapper.newInstance(TelegramMessage.class));
        } catch (DataAccessException ex) {
            log.info(String.format("User with id - %d, not found.", id));
        }
        return Optional.ofNullable(message);
    }

    @Override
    public Page<TelegramMessage> getMessagePage(Pageable pageable) {
        return null;
    }

    @Override
    public List<TelegramMessage> getAllMessages() {
        return template.query(findAll, rowMapper);
    }

    @Override
    public List<TelegramMessage> getMessagesByChatId(Long chatId) {
        SqlParameterSource param = new MapSqlParameterSource("chat_id", chatId);
        return template.query(findByChatId, param, rowMapper);
    }

    @Override
    public TelegramMessage save(TelegramMessage message) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        KeyHolder keyHolder = new GeneratedKeyHolder();

        params.addValue("user", message.getUser())
                .addValue("text", message.getText())
                .addValue("date", message.getDate())
                .addValue("chat_id", message.getChatId());

        template.update(save, params, keyHolder, new String[] { "id" });
        Integer id = 0;
        if(keyHolder.getKey() != null) {
            id = keyHolder.getKey().intValue();
        }

        message.setId(id);

        return message;
    }

    @Override
    public TelegramMessage updateText(TelegramMessage message) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", message.getId())
                .addValue("text", message.getText());
        template.update(updateText, params);
        return message;
    }

    @Override
    public void delete(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        template.update(deleteById, param);
    }
}
