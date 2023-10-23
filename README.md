# HybridSDK_Mobile_Automation

# Setup
- Install [Java](https://www.oracle.com/in/java/technologies/downloads/)
- Install [Maven](https://maven.apache.org/download.cgi)
- Install [node](https://nodejs.org)
- Install Appium `npm install -g appium`
- Install Appium Doctor `npm install -g appium-doctor`
- Check Appium driver installed `appium driver list`
- Install UIautomator2 driver `appium driver install uiautomator2`
- Install XCUItest driver `appium driver install xcuitest`
- Clone this repo `git clone https://github.com/Susilkumar-Udhayakumar/HybridSDK_Mobile_Automation.git`
- Install Allure `report brew install allure`

# Architecture


# Execution

command to run test in saucelab 
`mvn clean test -DsuiteXmlFile=androidsaucelabs.xml -DtestType=smoke -DCLOUD_DEVICE_NAME=Samsung_Galaxy_Tab_S7_Plus_real_us -DPLATFORM_VERSION=11 -DSAUCE_USERNAME=mrahim -DSAUCE_ACCESS_KEY=066bd0be-83a7-4239-9ca8-436ccef5798f -DRUN_ID=95790`


# Report and Log
- Logs are tracked in log folder
- Extent report are tracked under Extent Reports folder
- Inorder to create allure report run 'allure serve' in project folder


