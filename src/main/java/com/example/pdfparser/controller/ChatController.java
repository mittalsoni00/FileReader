package com.example.pdfparser.controller;




import com.example.pdfparser.dto.ChatRequest;
import com.example.pdfparser.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")




public class ChatController {

    @Autowired
    private OpenAiService openAiService;

    @PostMapping("/chat")
    public String chatWithGpt(@RequestBody ChatRequest request) {
        // Take the first message content as the prompt
        String prompt = request.getMessages().get(0).getContent();
        return openAiService.askChatGPT(prompt);
    }
}

//public class ChatController {
//
//    @Autowired
//    private OpenAiService openAiService;

//    @PostMapping("/chat")
//    public String chatWithGpt(@RequestBody String prompt) {
//        return openAiService.askChatGPT(prompt);
//    }
//    @PostMapping("/chat")
//    public String chatWithGpt(@RequestBody Map<String, Object> payload) {
//        String prompt = payload.get("prompt");
//        return openAiService.askChatGPT(prompt);
//    }

//}


