package com.example.pdfparser.service;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
//import lombok.Value;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PdfService {

    private final OpenAiService openAiService;

    @Value("${OPENAI_API_KEY}")
    private String apiKey;

    public PdfService() {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("OpenAI API Key is missing!");  // Handle missing API key
        }
        this.openAiService = new OpenAiService(apiKey);
    }

    public Map<String, String> extractDetailsFromText(String pdfText) {
        String prompt = "Extract the Name, Email, Opening Balance, and Closing Balance from the following bank statement:\n\n" + pdfText;

        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-4")  // Or use "gpt-3.5-turbo"
                .prompt(prompt)
                .maxTokens(100)
                .temperature(0.2)
                .build();

        List<CompletionChoice> choices = openAiService.createCompletion(request).getChoices();
        String extractedText = ((java.util.List<?>) choices).isEmpty() ? "No response" : choices.get(0).getText().trim();

        // Parse OpenAI response (assuming JSON format)
        Map<String, String> extractedData = new HashMap<>();
        extractedData.put("response", extractedText);
        return extractedData;
    }


//    public String extractTextFromPdf(InputStream inputStream) throws IOException {
//        try (PDDocument document = PDDocument.load(inputStream)) {
//            PDFTextStripper pdfStripper = new PDFTextStripper();
//            return pdfStripper.getText(document);
//        }
//    }
public String extractTextFromPdf(MultipartFile file) throws IOException {
    if (file.isEmpty()) {
        throw new IllegalArgumentException("Uploaded file is empty");
    }

    try (PDDocument document = PDDocument.load(file.getInputStream())) {
        PDFTextStripper stripper = new PDFTextStripper();
        return stripper.getText(document);
    }
}

    private Map<String, String> callLlmForExtraction(String text) {
        // Placeholder function for LLM API call
        Map<String, String> extractedData = new HashMap<>();

        // Simulated extraction (Replace with actual OpenAI API call)
        extractedData.put("Name", "John Doe");
        extractedData.put("Email", "john.doe@example.com");
        extractedData.put("Opening Balance", "$5000");
        extractedData.put("Closing Balance", "$4500");

        return extractedData;
    }
}
