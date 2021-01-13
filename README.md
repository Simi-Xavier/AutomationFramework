# Automation TestChallenge
##### Technologies Used:
* Selenium WebDriver (Open Source)
* JDK (Java Development Kit) 
* TestNG (Test Unit Framework) 
* Log4j (logging API) 
* Maven (Build Automation Tool) 
* Apache POI API (Read utilities for Excel - Test Data Handling) 
* Eclipse (Java Editor) 
* WebDriverManager - Library which allows to automate the management of the drivers. Found [here](https://github.com/bonigarcia/webdrivermanager)

##### Supported Browsers 
* Chrome
* Edge
* IE 

##### Supported OS
* Ubuntu (Tested)
* Windows 

##### Automation Framework Architecture: 
###### POM (Page Object Model)   
* The common functions are mentioned in the files under ‘Utilities’ 
* The page related functions in under ‘Pages’.
* The tests can be found under the ‘tests’ folder. 

##### TestData
* The test data used in the test cases are available in the testdata folder.  

##### Configuration
* File:  configuration.properties
* Configuration Values:

	* browser = "Chrome"
	* browserVersion = "87.0.4280.88"
	* url="http://automationpractice.com"

* The properties can be updated to run on other browsers such as IE, FireFox, Microsoft Edge and also on different test environments. 

