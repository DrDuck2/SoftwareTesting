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
  -  maven-surefire-report-plugin (version 2.22.1 or later)
  -  maven-compiler-plugin (version 3.11.0 or later)

- For WebDriverManager
  - [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) version 5.6.2 or later 

Dependencies are located in this project inside the pom.xml file.

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


## Setting up Continuous Integration with GitHub Actions

To seamlessly integrate your project with a CI service, you can follow the steps outlined in the [GitHub Actions documentation](https://docs.github.com/en/actions/automating-builds-and-tests/building-and-testing-java-with-maven).

The configuration file responsible for orchestrating the project setup and test execution in GitHub Actions is located at: .github/workflows/maven.yml.

## Workflow Overview

Whenever a new commit is pushed to the main branch on GitHub, the defined workflow in maven.yml will automatically:

    Build the project using Maven.
    Execute the first two tests specified in firstPageTest.xml and mainPageTest.xml.

## Customizing Test Execution

To customize which tests are run during the workflow, you can make modifications in either of the following ways:

    Modify maven.yml:
        Open .github/workflows/maven.yml and adjust the workflow steps accordingly to specify different test configurations.

    Modify pom.xml:
        Within the pom.xml file, locate the configuration related to test execution (commonly under the <build> or <plugins> section) and adjust it to meet your specific requirements.

This flexible setup allows you to easily control the scope and specifics of the tests run during the CI workflow.

  
