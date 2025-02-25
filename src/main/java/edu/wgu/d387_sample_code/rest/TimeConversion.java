package edu.wgu.d387_sample_code.rest;

import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

@Service
public class TimeConversion {

    public List<String> getTimeConversion() {

        ZonedDateTime startTime = ZonedDateTime.of(2025, 2, 21, 18, 0, 0, 0, ZoneId.of("America/New_York"));

        List<ZoneId> timeZones = List.of(
                ZoneId.of("America/New_York"),
                ZoneId.of("America/Denver"),
                ZoneId.of("UTC")
        );

        List<String> formattedTimes = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

        for (ZoneId zoneId : timeZones) {
            ZonedDateTime convertedTime = startTime.withZoneSameInstant(zoneId);
            String formattedTime = convertedTime.format(formatter);

            String timeZoneName = zoneId.getId().replace("_", " ").replace("/", " ");
            String[] parts = timeZoneName.split(" ");

            if (parts.length > 1) {
                timeZoneName = parts[1];
            } else {
                timeZoneName = parts[0];
            }

            formattedTimes.add(timeZoneName + ": " + formattedTime);
        }

        return formattedTimes;
    }
}
