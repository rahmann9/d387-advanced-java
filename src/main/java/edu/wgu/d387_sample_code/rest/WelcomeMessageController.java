package edu.wgu.d387_sample_code.rest;

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

        try {
            String[] langParts = lang.split("_");
            String language = langParts[0];
            String country = langParts.length > 1 ? langParts[1] : "";

            WelcomeMessageProvider provider = new WelcomeMessageProvider(language, country);
            String welcomeMessage = provider.getWelcomeMessage();

            responseMessage.append(welcomeMessage);

        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching welcome message", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseMessage.toString(), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<String> getRootWelcomeMessage(@RequestParam(value = "lang", defaultValue = "en_US") String lang) {
        StringBuilder responseMessage = new StringBuilder();

        try {
            String[] langParts = lang.split("_");
            String language = langParts[0];
            String country = langParts.length > 1 ? langParts[1] : "";

            WelcomeMessageProvider provider = new WelcomeMessageProvider(language, country);
            String welcomeMessage = provider.getWelcomeMessage();

            responseMessage.append(welcomeMessage);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching welcome message", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseMessage.toString(), HttpStatus.OK);
    }
}
