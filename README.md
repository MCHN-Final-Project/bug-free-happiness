                                     WEare Social Network - Final Project, Telerik Academy Alpha QA Track

I. Project description

WEare Social Network presents an innovative avenue for the pursuit of one's aspirations, where the synergy of offerings and desires flourishes harmoniously. Within this digital ecosystem, a vibrant tapestry of connections forms, weaving a rich social fabric. By extending a helping hand, individuals forge new bonds, expand their knowledge horizons, catalyze their career trajectories, and, most profoundly, imbue the community with their unique essence.

II. Used technologies 

Our project utilizes a stack of various technologies and tools, including:

- Testing Framework: Java with Selenium WebDriver
- API Testing: Postman, RestAssured and Newman(Command-line collection runner for Postman)
- Issue Tracking: JIRA
- Performance Testing: Apache JMeter
- Unit Testing: JUnit 5
- Build and Dependency Management: Apache Maven

These technologies were instrumental in the successful development and testing of our project.

III. Achievements

- Manual Testing: Our team has diligently conducted extensive manual testing of the project, covering all critical functionalities. This includes testing user registration, login, and various user interactions to identify usability and user experience issues.

- Automated Testing: We have successfully implemented automated testing using industry-standard testing tools and frameworks. This includes testing API endpoints, UI interactions, and performance under various scenarios.

IV. Test Coverage

Our test cases cover a wide range of scenarios, including positive and negative test cases, boundary testing, and load testing. We have designed test suites to validate different aspects of the project, ensuring it meets the highest quality standards.

V. Project Setup and test execution
To set up the project locally for testing and development, follow these steps:

1. Clone the docker images from the docker repo into a container with: ```docker pull -a simvelinov/final_project_docker```. After pulling the images start the container. You are all set!
2. Clone the Project: Start by cloning the project repository to your local machine using the following command: git clone https://github.com/MCHN-Final-Project/bug-free-happiness.git
3. Install Dependencies
4. Configuration: Configure the project settings and environment variables, if applicable. 
5. Database Setup: 
6. Run the Application: The application will be accessible at [http://localhost:8000](http://localhost:8000) in your web browser.
7. Run Tests: To execute the project's test suite, you can run the tests using: 
Running the API tests trough newman is done with the following command:
```
newman run https://api.postman.com/collections/28771591-340edd31-5097-49e7-9ddf-e252913a9dce?access_key=PMAT-01HCW6NXBDK0WQVYJ4TX02KKDK -r htmlextra --reporter-htmlextra-export report.html --reporter-htmlextra-title "API Test Report" --reporter-htmlextra-showEnvironmentData --reporter-htmlextra-showHeaders
```
Running the RESTAssured and Selenium tests is done trough the included .bat file.
This command will run the automated tests and provide you with test results.

Please ensure that you fulfill all the prerequisites and project-specific requirements as described in the project documentation. If you encounter any issues during setup, feel free to reach out to our team for assistance.

VI. Resources
1. [Docker repo](https://hub.docker.com/repository/docker/simvelinov/final_project_docker/general)
2. [Final project repo](https://github.com/MCHN-Final-Project/bug-free-happiness/tree/main)
3. [Test Plan](https://drive.google.com/file/d/15puq5XWKrBGZwFQJVNuef5pEOeFsSnUt/view?usp=sharing)
4. [Initial/exploratory testing report](https://drive.google.com/file/d/1vYETJy5eL2VHwcle3cVUmDyQXMj6f1XT/view?usp=sharing)
5. [Test Cases in Jira](https://simvel.atlassian.net/jira/software/c/projects/Q5FP/boards/6)
6. [Logged bugs and issues in Jira](https://simvel.atlassian.net/jira/software/c/projects/Q5FP/boards/4)
7. [Newman report](https://drive.google.com/file/d/1taeWlZz4rAszB3xgrqydSJ97RNLk5rrX/view?usp=sharing) 
8. [Performance Testing report](https://drive.google.com/file/d/1gJTNUmRoOp7jWBWo-V6Ax-DcMHRXaX_4/view?usp=sharing)
9. [Automation Tests report](https://drive.google.com/file/d/1SsgADR_gCjW9W1M4PdwrOlXzWiFjYeEo/view?usp=sharing)
10. [Final Test Report]() 

VII. Authors
Simona Diamandieva-Ilieva - [github](https://github.com/SimonaDiamandievaIlieva)
Petar Hlebarov - [github]()
Simeon Velinov - [github](https://github.com/SimeonVelinov/General)
