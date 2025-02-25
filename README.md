#### D387 – ADVANCED JAVA
###A. 
    #Gitlab:
    https://gitlab.com/wgu-gitlab-environment/student-repos/nrahm29/d387-advanced-java.git

Note: Wait until you have completed all the following prompts before you create your copy of the repository branch history.


###B
## 1. Install the Landon Hotel scheduling application in your integrated development environment (IDE). Modify the Java classes of application to display a welcome message by doing the following:
# a.  Build resource bundles for both English and French (languages required by Canadian law). Include a welcome message in the language resource bundles.

- Created `messages_en_USA.properties` and `messages_fr_CA.properties` for English and French localized greetings.


# b.  Display the welcome message in both English and French by applying the resource bundles using a different thread for each language.

- Added `WelcomeMessageProvider.java` to retrieve localized welcome messages from the 'messages' resource bundle.

- Implemented `WelcomeMessageController.java` in the rest folder to handle requests and provide localized greetings to the front-end UI.

- In `app.component.ts`, added api endpoint to `WelcomeMessageProvider.java`.

- In `app.component.html`, added a header to display welcome message.

## 2.  Modify the front end to display the price for a reservation in currency rates for U.S. dollars ($), Canadian dollars (C$), and euros (€) on different lines.

- In `app.component.html`, added currency rates for U.S. dollars ($), Canadian dollars (C$), and euros (€).

## 3.  Display the time for an online live presentation held at the Landon Hotel by doing the following:
# a.  Write a Java method to convert times between eastern time (ET), mountain time (MT), and coordinated universal time (UTC) zones.

- Created `TimeConversion.java` to handle time-zone conversions.

- Created `TimeConversionController.java` in the rest folder to handle requests and provide localized time.

# b.  Use the time zone conversion method from part B3a to display a message stating the time in all three times zones in hours and minutes for an online, live presentation held at the Landon Hotel. The times should be displayed as ET, MT, and UTC.


C.  Explain how you would deploy the Spring application with a Java back end and an Angular front end to cloud services and create a Dockerfile using the attached supporting document "How to Create a Docker Account" by doing the following:

1.  Build the Dockerfile to create a single image that includes all code, including modifications made in parts B1 to B3. Commit and push the final Dockerfile to GitLab.
# Start with a base image containing a Java runtime
FROM openjdk:17-jdk-slim

# Set the working directory inside the Docker image
WORKDIR /app

# Copy the JAR file provided by mvn clean package
COPY ../../target/D387_sample_code-0.0.2-SNAPSHOT.jar app.jar

# Make the app accessible on port:8080 for the host machine
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]

#docker build -t spring-boot-docker1:spring-docker .
#docker run -p 8080:8080  spring-boot-docker1:spring-docker

2.  Test the Dockerfile by doing the following:

•   Create a Docker image of the current multithreaded Spring application.

•   Run the Docker image in a container and give the container a name that includes D387_[student ID].

•   Submit a screenshot capture of the running application with evidence it is running in the container.

3.  Describe how you would deploy the current multithreaded Spring application to the cloud. Include the name of the cloud service provider you would use.


Note: Remember to commit and push your changes to GitLab.