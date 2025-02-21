package messages;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class WelcomeMessageProvider {

    private Locale locale;
    private ResourceBundle resourceBundle;

    public WelcomeMessageProvider(){}

    public WelcomeMessageProvider(String language, String country) {
        this.locale = new Locale(language, country);
        this.resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    public String getWelcomeMessage() {
        try {
            return resourceBundle.getString("welcome");
        } catch (MissingResourceException e) {
            return "Welcome message not found for this locale.";
        }
    }
}
