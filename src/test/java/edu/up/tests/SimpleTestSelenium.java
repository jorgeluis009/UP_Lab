package edu.up.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class SimpleTestSelenium {
    private static String WINDOWS_DRIVER = "/chromedriver/windows/chromedriver.exe";
    private static String MAC_DRIVER = "/chromedriver/mac/chromedriver";

    @Test (groups = {"Basic"})
    public void ShouldOpenBrowser() {
        // arrange
        WebDriver driver = setupChromeDriver();

        // act
        driver.navigate().to("https://www.up.edu.mx/es");

        // assert

        driver.getTitle()
        driver.close();
    }

    @Test
    public void shouldReturnNumberOfLinks(){
        /* TODO */
        /*Add code to obtain the number of links and print to console*/

    }
    private WebDriver setupChromeDriver() {
        if (System.getProperty("os.name").contains("Mac")) {
            File cDriver = new File(SimpleTestSelenium.class.getResource(MAC_DRIVER).getFile());

            // Is it executable
            if (!cDriver.canExecute()) {
                cDriver.setExecutable(true);
            }
            System.setProperty("webdriver.chrome.driver", SimpleTestSelenium.class.getResource(MAC_DRIVER).getFile());

            // Now checking for existence of Chrome executable.'
            if (!new File("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome").exists()) {
                throw new RuntimeException();
            }
        } else{
            String sDriver =SimpleTestSelenium.class.getResource(WINDOWS_DRIVER).getFile();
            System.setProperty("webdriver.chrome.driver", urlDecode(sDriver) );
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        return new ChromeDriver(options);
    }

    private String urlDecode(String value)  {
        try {
            return java.net.URLDecoder.decode(value, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }
}
