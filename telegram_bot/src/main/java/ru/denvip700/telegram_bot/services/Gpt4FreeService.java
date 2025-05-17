package ru.denvip700.telegram_bot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.denvip700.telegram_bot.dto.from.ResponseFromGpt4Free;
import ru.denvip700.telegram_bot.dto.to.RequestToGpt4Free;


@Service
public class Gpt4FreeService {
    private final RestTemplate restTemplate;

    @Value("${ollama.gpt4free.url}")
    private String ollamaGpt4FreeUrl;

    @Autowired
    public Gpt4FreeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String askMLL(RequestToGpt4Free content) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestToGpt4Free> entity = new HttpEntity<>(content, headers);

        ResponseEntity<ResponseFromGpt4Free> response = restTemplate.postForEntity(ollamaGpt4FreeUrl, entity, ResponseFromGpt4Free.class);
        ResponseFromGpt4Free responseFromGpt4Free = response.getBody();
        if (responseFromGpt4Free != null) {
            return responseFromGpt4Free.getChoices().get(0).getMessage().getContent();
        }
        return "failed";
    }
}
