# HybridSDK_Mobile_Automation

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Writing Test Cases](#writing-test-cases)
- [Running Tests](#running-tests)
- [Reporting](#reporting)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This framework is designed to automate HybridSDK applications on both iOS and Android platforms, leveraging the power of Appium. It is primarily built in Java and incorporates the following essential tools and technologies:

1. **Maven**: Utilize Maven as a robust build automation and project management tool to streamline your test automation workflow.

2. **TestNG**: Implement TestNG to manage and execute your test cases efficiently, providing features for test prioritization, parallel test execution, and powerful reporting capabilities.

3. **Extent and Allure Reports**: Enhance visibility and insights into your test results by utilizing both Extent and Allure reporting frameworks. These tools help you generate comprehensive, user-friendly reports that facilitate better test analysis.
   
5. **Log4j**: Employ Log4j for comprehensive logging, making it easier to troubleshoot issues and monitor test execution.


## Features

This Appium Framework comes with a comprehensive set of features to simplify and enhance automation:

- **Cross-Platform Support**: Automate HybridSDK apps seamlessly on both iOS and Android platforms. Ensure that your tests run consistently across different mobile operating systems.

- **Page Object Pattern with Page Factory Implementation**: Employ the Page Object Pattern with Page Factory for efficient, maintainable, and reusable test scripts. Organize your code into structured page objects, making it easier to interact with app elements.

- **Built-In Test Reporting**: Generate detailed test reports with ease. The framework seamlessly integrates with Extent and Allure Reports, providing comprehensive insights into your test results.

- **Logging**: Capture and store log data during test execution. Logging helps in identifying and diagnosing issues, ensuring robust and reliable automation.

- **Fresh Release Integration**: Integrate with Fresh Release to streamline test case management, track test execution progress, and enhance collaboration within your development workflow.

- **Local and Sauce Labs Execution**: Run your tests locally for rapid development cycles or leverage the power of Sauce Labs for cloud-based mobile app testing. Enjoy the flexibility to execute your tests in various environments.


## Prerequisites

### For Local Execution

Before you can begin with local execution using this framework, ensure you have the following prerequisites:

1. **IDE**: An Integrated Development Environment (IDE) such as [Eclipse](https://www.eclipse.org/) or [IntelliJ IDEA](https://www.jetbrains.com/idea/) to develop and run your automation scripts.

2. **Appium Server**: Install and set up [Appium Server](http://appium.io/) to automate mobile applications.

3. **Emulator**: A mobile emulator for testing Android apps. You can use [Android Emulator](https://developer.android.com/studio/run/emulator) for Android testing.

4. **Simulator**: A simulator for testing iOS apps. You can use [Xcode Simulator](https://developer.apple.com/documentation/xcode/running_ios_simulator_in_applescript) for iOS testing.

5. **uiautomator2 Driver**: Ensure you have the [uiautomator2 driver](https://appium.io/docs/en/2.1/quickstart/uiauto2-driver/) set up for Android automation.

6. **xcuitest Driver**: Set up the [xcuitest driver](https://appium.github.io/appium-xcuitest-driver/4.16/setup/) for iOS automation.

These prerequisites are essential for local execution with this framework. Please make sure they are properly installed and configured before getting started.

## Sauce Labs Execution

- For executing tests on Sauce Labs, you only need to set up your Sauce Labs credentials (username and access key). No additional local prerequisites are required for cloud-based execution.

## Installation

To install the MyAppium Automation Framework, follow these steps:

1. **Clone the Repository**: Use the following command to clone the repository to your local machine:

   ```bash
   git clone https://github.com/your-username/myappium-automation.git

2. Once the repository is cloned, open the project in your preferred Integrated Development Environment (IDE), such as Eclipse or IntelliJ IDEA.

## Getting Started

To create a new automation project using the MyAppium Framework, follow these steps:

1. **Navigate to the Test Directory**: Go to the `src/test/java` directory within your project.

2. **Add Your Test Class**: In the `src/test/java` directory, create your test class as shown in the example image below:

   ![Test Class Example](images/test-class-example.png)

   Ensure that your test class follows the necessary structure and naming conventions, and contains your test methods for automating your mobile app.

3. **Include in TestNG XML Files**: Add your test class to the TestNG XML files for test execution. Configure your test suites to include the newly created test class.

With these steps, you can begin your journey in creating and running automated tests using the MyAppium Framework.

## Configuration

To configure your MyAppium Framework project, follow these steps:

1. **Update TestNG XML and Test Type**:
   - Open the `pom.xml` file in your project.
   - Update the TestNG XML file you want to run and specify the type of test you want to execute (e.g., smoke, sanity, regression).
   - This can be configured as shown in the example image below:

   ![TestNG Configuration Example](images/testng-configuration-example.png)

2. **Sauce Labs Configuration**:
   - To run tests on Sauce Labs, you need to set up two environment variables, `SAUCE_USERNAME` and `SAUCE_ACCESS_KEY`.
   - Configure these environment variables in your local development environment or CI/CD pipeline as shown in the example image below:

   ![Sauce Labs Configuration Example](images/saucelabs-configuration-example.png)


## Running Tests

To execute your tests with the MyAppium Framework, follow these steps:

### From TestNG File

1. **Run from TestNG File**:
   - Right-click on the TestNG XML file you've configured for your tests.
   - Select "Run As TestNG Test" from the context menu.
   - You can initiate your tests by following the steps as shown in the example image below:

   ![Running Tests from TestNG File](images/run-testng-example.png)

### As a Maven Project

1. **Run as a Maven Project**:
   - Right-click on your project in your IDE.
   - Select "Maven" from the context menu and click "Clean."
   - After the clean process completes, right-click on the project again.
   - Select "Maven" from the context menu and click "Test."
   - You can start your test execution as a Maven project by following the steps shown in the example image below:

   ![Running Tests as a Maven Project](images/run-maven-example.png)

These steps provide options for running your tests directly from the TestNG XML file or as part of a Maven project.

With these configurations, you can tailor your test execution based on the TestNG XML and set up the necessary environment variables for Sauce Labs integration.


