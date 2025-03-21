# D387 – ADVANCED JAVA
### B

## 1. Install the Landon Hotel scheduling application in your integrated development environment (IDE). Modify the Java classes of the application to display a welcome message by doing the following:

### a. Build resource bundles for both English and French (languages required by Canadian law). Include a welcome message in the language resource bundles.

- Created `messages_en_USA.properties` and `messages_fr_CA.properties` for English and French localized greetings.

### b. Display the welcome message in both English and French by applying the resource bundles using a different thread for each language.

- Added `WelcomeMessageProvider.java` to retrieve localized welcome messages from the 'messages' resource bundle.
- Implemented `WelcomeMessageController.java` in the `rest` folder to handle requests and provide localized greetings to the front-end UI.
- In `app.component.ts`, added an API endpoint to `WelcomeMessageProvider.java`.
- In `app.component.html`, added a header to display the welcome message.

## 2. Modify the front end to display the price for a reservation in currency rates for U.S. dollars ($), Canadian dollars (C$), and euros (€) on different lines.

- In `app.component.html`, added currency rates for U.S. dollars ($), Canadian dollars (C$), and euros (€).

## 3. Display the time for an online live presentation held at the Landon Hotel by doing the following:

### a. Write a Java method to convert times between Eastern Time (ET), Mountain Time (MT), and Coordinated Universal Time (UTC) zones.

- Created `TimeConversion.java` to handle time-zone conversions.
- Created `TimeConversionController.java` in the `rest` folder to handle requests and provide localized time.

### b. Use the time zone conversion method from part 3a to display a message stating the time in all three time zones in hours and minutes for an online, live presentation held at the Landon Hotel. The times should be displayed as ET, MT, and UTC.

- Added `displayPresentationTime` and `formatTime` methods to `app.component.ts` to get time from the controller and present it to the frontend in the HTML.
- Added the place where the time will be displayed in `app.component.html`.

## C. Explain how you would deploy the Spring application with a Java backend and an Angular frontend to cloud services and create a Dockerfile using the attached supporting document "How to Create a Docker Account" by doing the following:

### 1. Build the Dockerfile to create a single image that includes all code, including modifications made in parts B1 to B3. Commit and push the final Dockerfile to GitLab.

- Added a Dockerfile to create a Docker image.

### 2. Test the Dockerfile by doing the following:

- **Create a Docker image of the current multithreaded Spring application:**
  ```bash
  docker build -t spring-boot-docker1:spring-docker .

- **Create a Docker image of the current multithreaded Spring application:**
```bash
  docker run --name D387_012096094 -p 8080:8080 spring-boot-docker1:spring-docker
