package com.example.pdfparser.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

    @Value("${OPENAI_API_KEY}")
    private String openaiApiKey;

    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    @PostConstruct
    public void init() {
        System.out.println("OPENAI_API_KEY loaded in service: " + openaiApiKey);
    }

    public String askChatGPT(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openaiApiKey);  // ✅ Correct header

        // ✅ Build request body
        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "user", "content", prompt));

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-4o-mini");
        body.put("messages", messages);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        // ✅ Debugging Print Statements
        System.out.println("Headers: " + headers);
        System.out.println("Body: " + request.getBody());

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(OPENAI_API_URL, request, Map.class);
            Map responseBody = response.getBody();

            if (responseBody != null) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    return message.get("content").toString();
                }
            }
        } catch (HttpClientErrorException e) {
            // More detailed error message
            return "Error while calling OpenAI API: " + e.getStatusCode() + " - " + e.getResponseBodyAsString();
        } catch (Exception e) {
            return "Error while calling OpenAI API: " + e.getMessage();
        }

        return "No response from OpenAI.";
    }
}
