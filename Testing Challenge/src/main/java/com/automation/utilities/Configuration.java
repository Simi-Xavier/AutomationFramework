package com.automation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Configuration {

	static Logger log = Logger.getLogger(Configuration.class.getName());

	private static Properties properties;

	private final String propertyFilePath = "configuration.properties";

	private static WebDriver driver;

	private void readPropertiesFile() {

		InputStream inputStream = null;
		try {
			properties = new Properties();
			inputStream = new FileInputStream("src/test/resources/" + propertyFilePath);
			properties.load(inputStream);
		} catch (Exception e) {
			log.error("Error reading properties file ", e);
			e.printStackTrace();
			throw new RuntimeException("Configuration Properties File Not Found");
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public void initalizeDriver() {
		try {
			readPropertiesFile();
			String browserType = properties.getProperty("browser");
			String browserVersion = properties.getProperty("browserVersion");

			switch (browserType) {
			case "Chrome":
				WebDriverManager.chromedriver().version(browserVersion).setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");
				options.addArguments("enable-automation");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-infobars");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--disable-browser-side-navigation");
				options.addArguments("--disable-gpu");
				driver = new ChromeDriver(options);
				break;

			case "IExplorer":
				WebDriverManager.iedriver().version(browserVersion).setup();
				driver = new InternetExplorerDriver();
				break;
			case "Edge":
				WebDriverManager.edgedriver().version(browserVersion).setup();
				driver = new EdgeDriver();
				break;
			}
			log.info(String.format("Driver initialized"));
		} catch (Exception e) {
			log.fatal("Driver could not be initialized", e);
			throw e;
		}
	}

	public String getURL() {
		return properties.getProperty("url");
	}

	public WebDriver getDriverInstance() {
		return driver;
	}

}
