package com.org.baseclass;

import Utils.TimeOut;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    protected static WebDriver driver;
    private Properties properties;

    //create a constructor
    public BaseClass() throws IOException {
        try {
            properties = new Properties();
            String filePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
            FileInputStream inputStream = null;
            inputStream = new FileInputStream(filePath);
            properties.load(inputStream);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void setUp() {
        String browserName=properties.getProperty("browser");
        //logic
        if(browserName.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(browserName.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        //get url
        driver.get(properties.getProperty("baseUrl"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeOut.PAGE_WAIT_TIME));


    }


    public String getUserName(){return properties.getProperty("username");}
    public String getpassword(){return properties.getProperty("password");}





}
