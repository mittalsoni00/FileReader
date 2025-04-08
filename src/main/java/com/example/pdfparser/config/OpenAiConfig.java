package com.example.pdfparser.config;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

//@Configuration
//public class OpenAiConfig {
//
//    @Bean
//    public OpenAiService openAiService(@Value("${OPENAI_API_KEY}") String apiKey) {
//        if (apiKey == null || apiKey.isEmpty()) {
//            throw new IllegalStateException("OpenAI API Key is missing!");
//        }
//        return new OpenAiService(apiKey);
//    }
//}




@Configuration
public class OpenAiConfig {

    @Value("${OPENAI_API_KEY}")
    private String openAiApiKey;

//    @Bean
//    public OpenAiService openAiService() {
//        return new OpenAiService(openAiApiKey);
//    }
}

