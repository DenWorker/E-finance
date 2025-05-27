package ru.denvip700.telegram_bot.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.denvip700.telegram_bot.entity.UserForTelegramBot;
import ru.denvip700.telegram_bot.repository.UserForTelegramBotRepository;

import java.util.Optional;


@Service
public class UserForTelegramBotService {
    private final UserForTelegramBotRepository userForTelegramBotRepository;

    @Autowired
    public UserForTelegramBotService(UserForTelegramBotRepository userForTelegramBotRepository) {
        this.userForTelegramBotRepository = userForTelegramBotRepository;
    }

    public UserForTelegramBot findUserForTelegramBotByFirstName(String name) {
        return userForTelegramBotRepository.findUserForTelegramBotByFirstName(name);
    }

    public Optional<UserForTelegramBot> findUserForTelegramBotByUserName(String name) {
        return userForTelegramBotRepository.findUserForTelegramBotByUserName(name);
    }
}
