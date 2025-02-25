package edu.wgu.d387_sample_code.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

public class TimeConversionController {

    private final TimeConversion timeConversion;

    public TimeConversionController(TimeConversion timeConversion) {
        this.timeConversion = timeConversion;
    }


    @GetMapping("/TimeZone")
    public List<String> getTimeZoneConversion() {
        return timeConversion.getTimeConversion();
    }
}