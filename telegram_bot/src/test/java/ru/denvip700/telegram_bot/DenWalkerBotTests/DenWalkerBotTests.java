package ru.denvip700.telegram_bot.DenWalkerBotTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.denvip700.telegram_bot.bot.DenWalkerBot;
import ru.denvip700.telegram_bot.services.Gpt4FreeService;
import ru.denvip700.telegram_bot.services.UserForTelegramBotService;

public class DenWalkerBotTests {

    @Mock
    private Gpt4FreeService gpt4FreeService;

    @Mock
    private UserForTelegramBotService userForTelegramBotService;

    @InjectMocks
    private DenWalkerBot denWalkerBot;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        denWalkerBot = new DenWalkerBot("fake-token", gpt4FreeService, userForTelegramBotService);
    }

    @Test
    // TODO
    void testOnUpdateReceived_newUser() {
        // arrange

        // act

        // assert

    }
}
