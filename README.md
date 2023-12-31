# Software Testing [JPetStore](https://petstore.octoperf.com/actions/Catalog.action) demo application from [OctoPerf](https://octoperf.com/)

Welcome to the Software Testing project! This repository contains a Java project (Written in IntelliJ) focused on software testing using technologies such as Selenium, TestNG, and Maven.

## Overview

The goal of this project is to learn practices used in software testing by creating a simple automatic testing framework. Some of the practices include 'Wait' commands, Page Object Models, WebDriverManager, Cross Browser testing, Object Oriented programming in automatic testing, Integration with CI service and others.

## Prerequisites

If you want to contribute to the project, ensure you have following requirements before working on the project:

- [Java JDK 21 ](https://www.oracle.com/java/technologies/downloads/#java8)or later
- Apache Maven 3.9.2
- Chrome or Firefox browser (for WebDriver testing)

- Dependencies:
- From [Maven Repository](https://mvnrepository.com/)
  -  selenium-java (version 3.12.0 or later)
  -  testng (version 6.14.3 or later)
  -  maven-surefire-report-plugin (version 3.2.3 or later)
  -  maven-compiler-plugin (version 3.12.1 or later)

- For WebDriverManager
  - [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) version 5.6.2 or later 

Dependencies are located in this project inside the pom.xml file, use the same versions or later.

## Getting Started

To run the tests locally, follow these steps:

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/DrDuck2/SoftwareTesting.git

2. Open the project, Navigate to Build->Build Project
3. Navigate to Run->EditConfiguration-> Click on the Plus icon located far left -> Locate TestNG and click on it
4. Setup the test with:
   - Name: Of your choosing
   - Test Kind: Suite
   - Suite: Navigate to the project folder and select either "firstPageTestNG.xml" or "mainPageTest.xml" ( for other tests navigate to ./src/test/resources)
5. Apply and Run the test!


## Creating Reports

To create reports locally you can do two different things, either install Mavel as a plugin or install it as a software on your machine.

If you choose to [install](https://phoenixnap.com/kb/install-maven-windows) it on your machine follow these steps:

1. Open Command Prompt
2. Navigate to project folder
3. Run command: mvn test
4. Target file should be created inside the project folder
5. Navigate to ./target/surefire-reports/index.html
6. Report should open up in your browser

If you choose to install it as a plugin, follow these steps:
For IntelliJ:

  1. Navigate to the right side of the program
  2. If you installed plugin correctly, under the notifications (bell icon) there should be an 'm' (maven icon). Click on it
  3. On the top toolbar select "execute maven goal"
  4. Run: mvn test
  5. After finishing the same target file will apear in the project folder


## Setting up Continuous Integration with GitHub Actions

To seamlessly integrate your project with a CI service, you can follow the steps outlined in the [GitHub Actions documentation](https://docs.github.com/en/actions/automating-builds-and-tests/building-and-testing-java-with-maven).

The configuration file responsible for orchestrating the project setup and test execution in GitHub Actions is located at: .github/workflows/maven.yml.

To run more tests on every push, simply add them to the pom.xml file

## Workflow Overview

Whenever a new commit is pushed to the main branch on GitHub, the defined workflow in maven.yml will automatically:

    1. Build the project using Maven.
    2. Execute the first two tests specified in firstPageTest.xml and mainPageTest.xml.
    3. Create a 'docs' directory inside the workflow repository (Not your personal repo)
    4. Copy files from target/surefire-reports/ into 'docs' directory
    5. Create Artifacts for the target/surefire-reports/ (you can find artifacts in: actions-> select the workflow-> scroll down-> click on "test-reports")
      - it will download the same files that are inside target/surefire-reports/*
    6. Push 'docs' directory to your repository
    7. Clean up the workflow

Workflow pushes 'docs' directory to the repository so that we can use GitHubs Pages feature that allows us to display the test results online.

!CAUTION! - You have to give permission to 'Actions' so that it can Push files to your repository.
To do this you have to follow these steps:
  1. Go to settings
  2. Go to Actions
  3. Select General
  4. Scroll down to Workflow Permissions
  5. Select Read and Write permissions
Now GitHub Actions can Push files to your repository

## GitHub Pages Overview

GitHub Pages allows us to display test reports online. If you want to setup your own GitHub Page you have to follow these steps:
  1. Go to Settings
  2. Go to Pages
  3. Select your branch (mine is set to 'main')
  4. Select your file (it can be /(root) or /docs, since we copied the files to 'docs/' put /docs)
  5. Save the configuration

After every push to the repository on the main branch, workflow will build the project, execute tests and it will create reports on those tests. After that worfklow is done, Pages workflow will run and it will create a website for you to see these reports visually.

The link is usually in this format: 'https://username.github.io/repository-name'

## Customizing Test Execution

To customize which tests are run during the workflow, you can make modifications in either of the following ways:

    Modify maven.yml:
        Open .github/workflows/maven.yml and adjust the workflow steps accordingly to specify different test configurations.

    Modify pom.xml:
        Within the pom.xml file, locate the configuration related to test execution (commonly under the <build> or <plugins> section) and adjust it to meet your specific requirements.

This flexible setup allows you to easily control the scope and specifics of the tests run during the CI workflow.

## Exploring Additional Tests

Every test in this suite is designed to run on both Chrome and Firefox browsers. The configuration is visible in the respective ..testNG.xml files, where each test class awaits the "chrome" or "firefox" parameter to specify the driver.

Given the constraints of the JPetStore online demo application, which lacks an interactable user database, certain tests involving user creation or removal cannot be automated freely. As stated in their documentation database resets every 10 minutes.

The mainPageTestNG.xml and firstPageTestNG.xml represent the only automated tests and can be executed as demonstrated in the Getting Started section.

### Other Test Suites
Additionally, there are three other testNG.xml files located in ./src/test/resources. Please note that these tests are designed for manual execution and are not part of the automated CI/CD workflow due to the mentioned constraints.

Important Note: Testing with Chrome is analogous to testing with Firefox, as the driver switching is automated.

#### Usage of 'LoginPageTest'

Navigate to: ./src/test/resources/loginPageTestNG.xml.

The LoginPageTest suite includes two test cases:

    Logging in with Correct Credentials:
        For the first test, modify the "value" for the username to match an existing database entry (ensure the user is registered beforehand).
        Do this for both the Chrome and Firefox sections.

    Logging in with Incorrect Credentials:
        For the second test, alter the "value" for a username not present in the database (avoid using the previously registered username, for example use "blabla").
        Do this for both the Chrome and Firefox sections.

Execution Steps:

    Go to Run -> Edit Configurations -> '+' icon -> TestNG.
    Name: LoginPageTest.
    Test kind: Suite.
    Suite: navigate to ./src/test/resources/loginPageTestNG.xml.
    Apply and Run.

#### Usage of 'RegisterPageTest'

Navigate to: ./src/test/resources/registerPageTestNG.xml.

The RegisterPageTest includes three test cases:

    Register with userId that is not in the database:
        For the first test, modify the "value" for the username to a value not inside the database (user is not registered).
        Do this for both the Chrome and Firefox sections (don't put the same username in both sections, because the first one will register it).

    Register with minimum required data:
        For the second test, do the same as for the first one but don't put the same username.

    Register without the important data:
        Leave it as it is.

Execution Steps:

    Go to Run -> Edit Configurations -> '+' icon -> TestNG.
    Name: RegisterPageTest.
    Test kind: Suite.
    Suite: navigate to ./src/test/resources/registerPageTestNG.xml.
    Apply and Run.

#### Usage of 'DatabaseTest'

Navigate to: ./src/test/resources/databaseTestNG.xml.

The DatabaseTest includes one test case:

    This test is made to replicate the sequence of users clicking and input. It will first try to login with bad credentials, then it will click on the register button, it will register the user with the same credentials, and then it will navigate back to the login page and try to login the user with the same credentials again.
    It is called DatabaseTest because all the tests inside the sequence are based on the database's functionality.
    For this test, it is only necessary to replace the username with the incorrect username (or the one that is not in the database).

Execution Steps:

    Go to Run -> Edit Configurations -> '+' icon -> TestNG.
    Name: DatabaseTest.
    Test kind: Suite.
    Suite: navigate to ./src/test/resources/DatabaseTestNG.xml.
    Apply and Run.

Note: This setup wouldn't be necessary if the demo application had an interactable database, and we could simply delete the user after registering it or place a user inside the database before logging in.
    
  
