package ru.denvip700.telegram_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.denvip700.telegram_bot.entity.UserForTelegramBot;


@Repository
public interface UserForTelegramBotRepository extends JpaRepository<UserForTelegramBot, Long> {
    UserForTelegramBot findUserForTelegramBotByFirstName(String firstName);

    UserForTelegramBot findUserForTelegramBotByUserName(String userName);
}
