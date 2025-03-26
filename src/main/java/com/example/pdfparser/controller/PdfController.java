package com.example.pdfparser.controller;

import com.example.pdfparser.service.PdfService;
import com.example.pdfparser.util.PdfParserUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PdfController {

    @Autowired
    private  PdfService pdfService;
    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }
    @PostMapping(value = "/parse-pdf", consumes = "multipart/form-data")

    public ResponseEntity<Map<String, String>> parsePdf(@RequestParam("file") MultipartFile file) {
        try {
            String extractedText = PdfParserUtil.extractText(file);
            Map<String, String> extractedData = pdfService.extractDetailsFromText(extractedText);
            return ResponseEntity.ok(extractedData);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to process PDF"));
        }
    }



    //last working method below 200OK
//    public ResponseEntity<Map<String, String>> parsePdf(@RequestParam("file") MultipartFile file) {
//        Map<String, String> response = new HashMap<>();
//        try {
//            // Convert MultipartFile to PDDocument
//            PDDocument document = PDDocument.load(file.getInputStream());
//
//            // Extract text from the document
//            PDFTextStripper pdfStripper = new PDFTextStripper();
//            String extractedText = pdfStripper.getText(document);
//
//            // Close document
//            document.close();
//
//            // Put extracted text into response
//            response.put("message", "✅ File processed successfully!");
//            response.put("extractedText", extractedText);
//
//            return ResponseEntity.ok(response);
//        } catch (IOException e) {
//            response.put("error", "❌ Failed to process PDF: " + e.getMessage());
//            return ResponseEntity.badRequest().body(response);
//        }
//    }
//this below is the function getting 200 OK
//    @PostMapping(value = "/parse-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Map<String, String>> parsePdf(@RequestParam("file") MultipartFile file) {
//        System.out.println("✅ Request reached parsePdf method!");
//        System.out.println("✅ Received file: " + file.getOriginalFilename());
//
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "File received successfully");
//        return ResponseEntity.ok(response);
//    }







//advanced
//    @PostMapping(value = "/parse-pdf",
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Map<String, String>> parsePdf(
//            @RequestParam("file") MultipartFile file) {
//        try {
//            String extractedText = pdfService.extractTextFromPdf(file);
//
//            Map<String, String> response = new HashMap<>();
//            response.put("content", extractedText);
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            Map<String, String> errorResponse = new HashMap<>();
//            errorResponse.put("error", "Failed to parse PDF: " + e.getMessage());
//            return ResponseEntity.badRequest().body(errorResponse);
//        }
//    }

    //claude:-
//    @PostMapping(value = "/parse-pdf", consumes = "multipart/form-data")
//    public ResponseEntity<Map<String, String>> parsePdf(@RequestPart("file") MultipartFile file) {
//        try {
//            String extractedText = pdfService.extractTextFromPdf(file);
//
//            Map<String, String> response = new HashMap<>();
//            response.put("content", extractedText);
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            Map<String, String> errorResponse = new HashMap<>();
//            errorResponse.put("error", "Failed to parse PDF: " + e.getMessage());
//            return ResponseEntity.badRequest().body(errorResponse);
//        }
//    }

//    @PostMapping(value = "/parse-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Map<String, String>> parsePdf(@RequestPart("file") MultipartFile file) {
//        System.out.println("✅ Request reached parsePdf method!");
//        System.out.println("✅ Received file: " + file.getOriginalFilename());
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "File received successfully");
//        return ResponseEntity.ok(response);
//    }
    //last working this above





//    public class PdfController {

//        @PostMapping(value = "/test-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//        public ResponseEntity<Map<String, String>> testUpload(@RequestParam("file") MultipartFile file) {
//            System.out.println("✅ Request reached testUpload method!");
//
//            if (file == null) {
//                System.out.println("❌ MultipartFile is NULL!");
//                return ResponseEntity.badRequest().body(Map.of("error", "File is missing!"));
//            }
//
//            System.out.println("✅ Received file: " + file.getOriginalFilename());
//            System.out.println("✅ Content Type: " + file.getContentType());
//
//            Map<String, String> response = new HashMap<>();
//            response.put("message", "File received successfully");
//            return ResponseEntity.ok(response);
//        }








//    @PostMapping(value = "/parse-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Map<String, String>> parsePdf(@RequestParam("file") MultipartFile file) {
//        System.out.println("✅ Request reached parsePdf method!");
//        System.out.println("✅ Received file: " + file.getOriginalFilename());
//
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "File received successfully");
//        return ResponseEntity.ok(response);
//    }




//    @PostMapping(value = "/upload", consumes = "multipart/form-data")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        System.out.println("🚀 Received request for file upload!");
//        System.out.println("Headers: " + Arrays.toString(file.getContentType().split("/")));
//
//        if (file.isEmpty()) {
//            System.out.println("❌ No file received!");
//            return ResponseEntity.badRequest().body("No file uploaded!");
//        }
//        System.out.println("✅ File received: " + file.getOriginalFilename());
//        return ResponseEntity.ok("✅ File received: " + file.getOriginalFilename());
//    }
//    @PostMapping(value = "/upload", consumes = "multipart/form-data")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("❌ No file uploaded!");
//        }
//        return ResponseEntity.ok("✅ File received: " + file.getOriginalFilename());
//    }
//    @PostMapping(value = "/parse-pdf", consumes = "multipart/form-data")
//    public ResponseEntity<Map<String, String>> parsePdf(@RequestParam("file") MultipartFile file) {
//        try {
//            System.out.println("Received file: " + file.getOriginalFilename()); // Debug log
//            Map<String, String> extractedData = pdfService.processPdf(file);
//            return ResponseEntity.ok(extractedData);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("error", "Failed to process PDF: " + e.getMessage()));
//        }
//    }
//@PostMapping(value = "/parse-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//public ResponseEntity<Map<String, String>> parsePdf(@RequestParam("file") MultipartFile file) {
//    System.out.println("✅ Received file: " + file.getOriginalFilename()); // Debugging
//    Map<String, String> response = new HashMap<>();
//    response.put("message", "File received successfully");
//    return ResponseEntity.ok(response);
//}
//
//@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//    return ResponseEntity.ok("✅ File received: " + file.getOriginalFilename());
//}
//@PostMapping(value = "/parse-pdf", consumes = {"multipart/form-data", "application/pdf"})
//public ResponseEntity<Map<String, String>> parsePdf(@RequestParam("file") MultipartFile file) {
//    System.out.println("✅ Request reached parsePdf method!");
//    System.out.println("✅ Received file: " + file.getOriginalFilename());
//    Map<String, String> response = new HashMap<>();
//    response.put("message", "File received successfully");
//    return ResponseEntity.ok(response);
//}


//    @PostMapping("/test")
//    public ResponseEntity<String> testEndpoint() { //because it has no parameters, it is working fine
//        System.out.println("✅ Request reached /test endpoint!");
//        return ResponseEntity.ok("Test Successful");
//    }method for testing only




}
