package edu.wgu.d387_sample_code.rest;

import messages.WelcomeMessageProvider;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class WelcomeMessageController {

    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcomeMessage(@RequestParam("lang") String lang) {
        StringBuilder responseMessage = new StringBuilder();

        // Thread for fetching the English welcome message
        Thread englishThread = new Thread(() -> {
            try {
                WelcomeMessageProvider provider = new WelcomeMessageProvider("en", "US");
                String englishMessage = provider.getWelcomeMessage();
                responseMessage.append("English: ").append(englishMessage).append("\n");
            } catch (Exception e) {
                responseMessage.append("Error fetching English message\n");
            }
        });

        // Thread for fetching the French welcome message
        Thread frenchThread = new Thread(() -> {
            try {
                WelcomeMessageProvider provider = new WelcomeMessageProvider("fr", "CA");
                String frenchMessage = provider.getWelcomeMessage();
                responseMessage.append("French: ").append(frenchMessage);
            } catch (Exception e) {
                responseMessage.append("Error fetching French message");
            }
        });

        englishThread.start();
        frenchThread.start();

        try {
            englishThread.join();
            frenchThread.join();
        } catch (InterruptedException e) {
            return new ResponseEntity<>("Error waiting for threads", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseMessage.toString(), HttpStatus.OK);
    }
}

