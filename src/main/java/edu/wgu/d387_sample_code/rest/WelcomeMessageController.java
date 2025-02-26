package edu.wgu.d387_sample_code.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class WelcomeMessageController {
    private final ExecutorService executor = Executors.newFixedThreadPool(2); // 2 threads for en_US and fr_CA

    @GetMapping("/welcome")
    public ResponseEntity<List<String>> getWelcomeMessage(@RequestParam("lang") String lang) {
        List<String> messages = new ArrayList<>();
        if ("en_US".equals(lang) || "fr_CA".equals(lang)) {

            List<Callable<Void>> tasks = new ArrayList<>();

            if ("en_US".equals(lang)) {
                tasks.add(() -> {
                    WelcomeMessageProvider providerEN = new WelcomeMessageProvider("en", "US");
                    String messageEN = providerEN.getWelcomeMessage();
                    synchronized (messages) {
                        messages.add(messageEN);
                    }
                    return null;
                });
            }

            if ("fr_CA".equals(lang)) {
                tasks.add(() -> {
                    WelcomeMessageProvider providerFR = new WelcomeMessageProvider("fr", "CA");
                    String messageFR = providerFR.getWelcomeMessage();
                    synchronized (messages) {
                        messages.add(messageFR);
                    }
                    return null;
                });
            }

            try {
                executor.invokeAll(tasks);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ResponseEntity<>(List.of("Error fetching welcome messages"), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return ResponseEntity.ok(messages);
        } else {
            return new ResponseEntity<>(List.of("Unsupported language"), HttpStatus.BAD_REQUEST);
        }
    }
}
