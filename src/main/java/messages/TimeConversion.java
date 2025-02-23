package messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

@Service
public class TimeConversion {


    public List<String> getTimeConversion() {
        // Fixed start time: 6:00 PM EST
        ZonedDateTime startTime = ZonedDateTime.of(2025, 2, 21, 18, 0, 0, 0, ZoneId.of("America/New_York"));

        // Define time zones
        List<ZoneId> timeZones = List.of(
                ZoneId.of("America/New_York"),  // Eastern Time (EST)
                ZoneId.of("America/Denver"),    // Mountain Time (MST)
                ZoneId.of("UTC")                // Coordinated Universal Time (UTC)
        );

        List<String> formattedTimes = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

        for (ZoneId zoneId : timeZones) {
            ZonedDateTime convertedTime = startTime.withZoneSameInstant(zoneId);
            String formattedTime = convertedTime.format(formatter);
            String timeZoneName = zoneId.getId().replace("_", " ").replace("/", " ").split(" ")[1];
            formattedTimes.add(timeZoneName + ": " + formattedTime);
        }

        return formattedTimes;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RestController
    public static class WelcomeMessageController {

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
}
