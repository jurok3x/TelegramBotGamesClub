package com.ykotsiuba.soloveibot.dao;

import com.ykotsiuba.soloveibot.entity.TelegramMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TelegramMessageDao {

    Optional<TelegramMessage> getMessageById(Integer id);
    Page<TelegramMessage> getMessagePage(Pageable pageable);
    List<TelegramMessage> getAllMessages();
    List<TelegramMessage> getMessagesByChatId(Long chatId);
    TelegramMessage save(TelegramMessage message);
    void delete(Integer id);

}
