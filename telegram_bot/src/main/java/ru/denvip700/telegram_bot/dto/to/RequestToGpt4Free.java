package ru.denvip700.telegram_bot.dto.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.denvip700.telegram_bot.dto.MessageWithGpt4Free;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestToGpt4Free {
    private String model;
    private List<MessageWithGpt4Free> messages;
}
