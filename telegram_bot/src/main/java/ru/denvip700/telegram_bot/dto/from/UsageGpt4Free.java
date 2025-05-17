package ru.denvip700.telegram_bot.dto.from;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsageGpt4Free {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
}
