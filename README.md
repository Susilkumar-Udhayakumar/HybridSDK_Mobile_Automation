# HybridSDK_Mobile_Automation

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Reporting](#reporting)

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

## Architecture

![Architecture diagram](https://github.com/Susilkumar-Udhayakumar/HybridSDK_Mobile_Automation/assets/108650907/3d0709f1-7830-488a-99e2-a94cde28e09d)


## Prerequisites

### For Local Execution

Before you can begin with local execution using this framework, ensure you have the following prerequisites:

1. **IDE**: An Integrated Development Environment (IDE) such as [Eclipse](https://www.eclipse.org/) or [IntelliJ IDEA](https://www.jetbrains.com/idea/) to develop and run your automation scripts.

2. **Appium Server**: Install and set up [Appium Server](http://appium.io/) to automate mobile applications.

3. **Emulator**: A mobile emulator for testing Android apps. You can use [Android Emulator](https://developer.android.com/studio/run/emulator) for Android testing.

4. **Simulator**: A simulator for testing iOS apps. You can use [Xcode Simulator](https://developer.apple.com/documentation/xcode/running_ios_simulator_in_applescript) for iOS testing.

5. **uiautomator2 Driver**: Ensure you have the [uiautomator2 driver](https://appium.io/docs/en/2.1/quickstart/uiauto2-driver/) set up for Android automation.

6. **xcuitest Driver**: Set up the [xcuitest driver](https://appium.github.io/appium-xcuitest-driver/4.16/setup/) for iOS automation.

7. **Allure Report**: Set up the [allure report](https://allurereport.org/docs/gettingstarted/installation/) for reporting.

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

  ![Test class](https://github.com/Susilkumar-Udhayakumar/HybridSDK_Mobile_Automation/assets/108650907/4cffb5ba-d487-4d08-8075-77ccf5c8f3af)


   Ensure that your test class follows the necessary structure and naming conventions, and contains your test methods for automating your mobile app.

3. **Include in TestNG XML Files**: Add your test class to the TestNG XML files for test execution. Configure your test suites to include the newly created test class.

With these steps, you can begin your journey in creating and running automated tests using the MyAppium Framework.

## Configuration

To configure your MyAppium Framework project, follow these steps:

1. **Update TestNG XML and Test Type**:
   - Open the `pom.xml` file in your project.
   - Update the TestNG XML file you want to run and specify the type of test you want to execute (e.g., smoke, sanity, regression).
   - This can be configured as shown in the example image below:

  ![Update Testng xml](https://github.com/Susilkumar-Udhayakumar/HybridSDK_Mobile_Automation/assets/108650907/e0b9c84b-002c-49ef-87c9-016e2751d1ac)


2. **Sauce Labs Configuration**:
   - To run tests on Sauce Labs, you need to set up two environment variables, `SAUCE_USERNAME` and `SAUCE_ACCESS_KEY`.
   - Configure these environment variables in your local development environment or CI/CD pipeline


## Running Tests

To execute your tests with the MyAppium Framework, follow these steps:

### From TestNG File

1. **Run from TestNG File**:
   - Right-click on the TestNG XML file you've configured for your tests.
   - Select "Run As TestNG Test" from the context menu.
   - You can initiate your tests by following the steps as shown in the example image below:

   ![Testng xml](https://github.com/Susilkumar-Udhayakumar/HybridSDK_Mobile_Automation/assets/108650907/fa6f33fd-8292-452c-bc08-cf28a61db89d)

### As a Maven Project

1. **Run as a Maven Project**:
   - Right-click on your project in your IDE.
   - Select "Maven" from the context menu and click "Clean."
   - After the clean process completes, right-click on the project again.
   - Select "Maven" from the context menu and click "Test."
   - You can start your test execution as a Maven project by following the steps shown in the example image below:

    ![Run as Maven Project](https://github.com/Susilkumar-Udhayakumar/HybridSDK_Mobile_Automation/assets/108650907/7a748559-3bd3-442b-b91c-4ce16fb63986)


These steps provide options for running your tests directly from the TestNG XML file or as part of a Maven project.

With these configurations, you can tailor your test execution based on the TestNG XML and set up the necessary environment variables for Sauce Labs integration.

### Sauce lab from local

1. **Run into sauce lab from local**
  - Open terminal
  - Go to project location
  - Run maven command `mvn clean test -DsuiteXmlFile=androidsaucelabs.xml -DtestType=smoke -DCLOUD_DEVICE_NAME=Samsung_S7_Plus_real_us -DPLATFORM_VERSION=11 -DSAUCE_USERNAME=***** -DSAUCE_ACCESS_KEY=**** -DRUN_ID=95790` 

## Reporting

### Logs

For tracing logs log4j is used, call below methods for logging information. Logged information are traced inside log folder
   - info() for tracing launch and termination details of test and api.
   - debug() for logging debug information such as api response string and action result
   - message() for logging all test step
   - pass() for logging success test result
   - fail() for logging failed test result
   - skip() for logging skipped test result

sample log report:
![Sample log report](https://github.com/Susilkumar-Udhayakumar/HybridSDK_Mobile_Automation/assets/108650907/7004efd0-c668-440e-ab88-0f9d8650153f)

### Extent Report

Extent reports are setup and generations are managed by testlistner, After completion of execution reports are tracked under Extent Report folder. Use below method for logging addtion step information
   - message() for tracing test step
   - logAssertTrue() for assert positive flow
   - logAssertFalse() for asserting negative flow
   - logAssertEqual() for comparision

Sample extent report:
![Sample extent report](https://github.com/Susilkumar-Udhayakumar/HybridSDK_Mobile_Automation/assets/108650907/0849dff6-fdbe-4123-96f8-6c60e0b6dd75)

 
