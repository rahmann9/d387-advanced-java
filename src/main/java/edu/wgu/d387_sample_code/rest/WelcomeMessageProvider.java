package edu.wgu.d387_sample_code.rest;

import java.util.Locale;
import java.util.ResourceBundle;

public class WelcomeMessageProvider {

    private Locale locale;
    private ResourceBundle resourceBundle;

    public WelcomeMessageProvider(String language, String country) {
        this.locale = new Locale(language, country);
        this.resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    public String getWelcomeMessage() {
        try {
            return resourceBundle.getString("welcome");
        } catch (Exception e) {
            return "Welcome message not found for this locale.";
        }
    }
}
