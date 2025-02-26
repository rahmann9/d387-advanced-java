#### D387 – ADVANCED JAVA
###A. 
    #Gitlab:
    https://gitlab.com/wgu-gitlab-environment/student-repos/nrahm29/d387-advanced-java.git

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
- Added displayPresentationTime and formatTime methods to `app.component.ts` in order to get time from the controller and present it to the frontend in the html

- Added the place where the time will be displayed in `app.component.html`

C.  Explain how you would deploy the Spring application with a Java back end and an Angular front end to cloud services and create a Dockerfile using the attached supporting document "How to Create a Docker Account" by doing the following:

1.  Build the Dockerfile to create a single image that includes all code, including modifications made in parts B1 to B3. Commit and push the final Dockerfile to GitLab.

- Added a Dockerfile to create docker image.
# 2.  Test the Dockerfile by doing the following:

•   Create a Docker image of the current multithreaded Spring application.

- docker build -t spring-boot-docker1:spring-docker .

•   Run the Docker image in a container and give the container a name that includes D387_[student ID].

- docker run --name D387_012096094 -p 8080:8080  spring-boot-docker1:spring-docker

•   Submit a screenshot capture of the running application with evidence it is running in the container.
 
-Screen shot is at `Docker Container Running Proof Screenshot.png`

# 3.  Describe how you would deploy the current multithreaded Spring application to the cloud. Include the name of the cloud service provider you would use.

- I would select Microsoft Azure as the cloud provider for deploying my multithreaded Spring web application.
- First, I would create an Azure Container Registry to securely store my Docker image. This registry will act as a centralized location for storing images before deployment.
- Next, I would build the Docker image for my Spring web application and tag it with a version tag like latest. I would then push this image to the Azure Container Registry using the Azure CLI.
- After pushing the image to ACR, I would create an Azure Container Instance using the image stored in ACR. I would configure the container instance with appropriate settings such as CPU, memory, environment variables, and port mappings.
- Finally, I would deploy the Azure Container Instance, making it accessible via a public IP or DNS. This would run my multithreaded Spring application in the cloud, allowing it to be live and accessible online.