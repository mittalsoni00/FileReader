package com.example.api_reader_new;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication // Now scans the entire com.example.api_reader_new.*
//@ComponentScan(basePackages = {"com.example.pdfparser"})
@SpringBootApplication(scanBasePackages = "com.example.pdfparser")

public class ApiReaderNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiReaderNewApplication.class, args);
    }
}





//package com.example.api_reader_new;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
////import javax.servlet.MultipartConfigElement;
//
//
//@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.pdfparser.controller", "com.example.pdfparser.service"})
//public class ApiReaderNewApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(ApiReaderNewApplication.class, args);
//    }
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        return new MultipartConfigElement("");
//    }

