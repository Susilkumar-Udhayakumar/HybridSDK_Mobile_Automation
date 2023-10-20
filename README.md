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

1. For executing tests on Sauce Labs, you only need to set up your Sauce Labs credentials (username and access key). No additional local prerequisites are required for cloud-based execution.

2. With these simple steps, you can leverage the power of Sauce Labs for cloud-based test execution, allowing you to test your mobile apps on a wide range of real devices and configurations.

3. Please ensure that your Sauce Labs credentials are kept secure and not shared publicly in your code repositories.
