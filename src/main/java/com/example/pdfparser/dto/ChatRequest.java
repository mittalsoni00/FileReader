package com.example.pdfparser.dto;

import java.util.List;

public class ChatRequest {
    private String model;
    private List<ChatMessage> messages;

    // Getters and setters
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public List<ChatMessage> getMessages() { return messages; }
    public void setMessages(List<ChatMessage> messages) { this.messages = messages; }
}

