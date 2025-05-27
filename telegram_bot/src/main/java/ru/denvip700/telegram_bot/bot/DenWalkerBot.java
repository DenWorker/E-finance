package ru.denvip700.telegram_bot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.denvip700.telegram_bot.dto.MessageWithGpt4Free;
import ru.denvip700.telegram_bot.dto.to.RequestToGpt4Free;
import ru.denvip700.telegram_bot.entity.UserForTelegramBot;
import ru.denvip700.telegram_bot.services.Gpt4FreeService;
import ru.denvip700.telegram_bot.services.UserForTelegramBotService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Component
public class DenWalkerBot extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(DenWalkerBot.class);

    private final Gpt4FreeService gpt4FreeService;
    private final UserForTelegramBotService userForTelegramBotService;

    private final List<MessageWithGpt4Free> messages;

    {
        messages = new ArrayList<>();
        messages.add(new MessageWithGpt4Free("user",
                "/nТы очень агрессивный бот" +
                        "/nОбщайся с обзывательством и грубостью" +
                        "/nВсегда добавляй имя человека, которому пишешь"));
    }

    @Autowired
    public DenWalkerBot(@Value("${bot.token}") String botToken, Gpt4FreeService gpt4FreeService, UserForTelegramBotService userForTelegramBotService) {
        super(botToken);
        this.gpt4FreeService = gpt4FreeService;
        this.userForTelegramBotService = userForTelegramBotService;
    }

    // TODO поправить: god class; если много юзеров, то будет мешанина в messages; добавить абстракции

    @Override
    public void onUpdateReceived(Update update) {
        // From telegram
        User user = update.getMessage().getFrom();

        // From my DB
        Optional<UserForTelegramBot> optionalUser = userForTelegramBotService.findUserForTelegramBotByUserName(user.getUserName());
        UserForTelegramBot userForTelegramBot = optionalUser.orElseGet(() -> {
            UserForTelegramBot newUser = new UserForTelegramBot();
            newUser.setUserName(user.getUserName());
            newUser.setDisadvantages("Любой");
            return newUser;
        });

        String textForSend = richTextForSend(userForTelegramBot, update.getMessage().getText());
        log.info(textForSend);

        // ASK
        String result = gpt4FreeService.askMLL(makeRequest(textForSend));

        // To telegram
        log.info("\nПринятое сообщение:\n {}", result);

        messages.add(new MessageWithGpt4Free("assistant", result));
        try {
            execute(new SendMessage(update.getMessage().getChatId().toString(), result));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return "DenWalkerBot";
    }

    public RequestToGpt4Free makeRequest(String receivedText) {
        RequestToGpt4Free requestToGpt4Free = new RequestToGpt4Free();
        requestToGpt4Free.setModel("gpt-4o-mini");

        messages.add(new MessageWithGpt4Free("user", receivedText));

        requestToGpt4Free.setMessages(messages);
        return requestToGpt4Free;
    }

    public String richTextForSend(UserForTelegramBot userForTelegramBot, String textForSend) {
        String[] disadvantages = userForTelegramBot.getDisadvantages().split("\n");

        String disadvantage;
        if (new Random().nextInt(100) <= 50) {
            disadvantage = "Недостаток человека: " + disadvantages[new Random().nextInt(disadvantages.length)] + "\n";
        } else {
            disadvantage = "Недостаток человека: любой \n";
        }

        textForSend = "\nОтправляемый текст:\n"
                + textForSend + "\n"
                + "Имя человека, который отправляет сообщение:\n"
                + "@" + userForTelegramBot.getUserName() + "\n"
                + disadvantage;
        return textForSend;
    }
}
