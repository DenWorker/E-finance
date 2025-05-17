package ru.denvip700.telegram_bot.dto.from;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.denvip700.telegram_bot.dto.MessageWithGpt4Free;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceFromGpt4Free {
    private int index;
    private MessageWithGpt4Free message;
    private String finish_reason;
}
