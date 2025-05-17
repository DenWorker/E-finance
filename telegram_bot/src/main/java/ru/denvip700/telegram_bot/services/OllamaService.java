package ru.denvip700.telegram_bot.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OllamaService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${ollama.api.url}")
    private String ollamaApiUrl;

    @Autowired
    public OllamaService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String askMLL(String userMessage) {
        Map<String, Object> body = new HashMap<>();
        body.put("model", "llama3");

        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", userMessage);

        body.put("messages", List.of(message));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    ollamaApiUrl + "/v1/chat/completions", HttpMethod.POST, entity, String.class);
            return getAnswer(response.getBody());
        } catch (Exception e) {
            return "Ошибка при запросе к Ollama API: " + e.getMessage();
        }
    }

    public String getAnswer(String body) {
        try {
            JsonNode root = objectMapper.readTree(body);
            return root.at("/choices/0/message/content").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
