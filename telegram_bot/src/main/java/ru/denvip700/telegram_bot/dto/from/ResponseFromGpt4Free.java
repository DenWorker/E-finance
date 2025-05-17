package ru.denvip700.telegram_bot.dto.from;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFromGpt4Free {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<ChoiceFromGpt4Free> choices;
    private UsageGpt4Free usage;
}
