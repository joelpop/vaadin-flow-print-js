package org.vaadin.addons.joelpop.util;

import com.vaadin.testbench.TestBenchTestCase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.vaadin.addons.joelpop.webdriver.WebDriverFactory;
import org.vaadin.addons.joelpop.webdriver.WebDriverManagerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseTestCase extends TestBenchTestCase {
    public static final String WEBDRIVER_PROPERTY = "webdriver";
    public static final String WEBDRIVER_DEFAULT = System.getProperty("wdm.defaultBrowser", "chrome");
    public static final String SERVER_PORT_PROPERTY = "server.port";
    public static final String SERVER_PORT_DEFAULT = "localhost:8080";

    private WebDriverManager webDriverManager;


    /**
     * Select and initialize the web driver manager.
     */
    protected void initWebDriverManager() {
        // get web driver manager
        webDriverManager =
                WebDriverManagerFactory.getInstanceFor(System.getProperty(WEBDRIVER_PROPERTY, WEBDRIVER_DEFAULT));
        assertThat(webDriverManager.getBrowserPath())
                .as("WebDriverManager")
                .isPresent();
    }

    /**
     * Instantiate a web driver for TestBench and open the browser.
     */
    protected void openWebBrowser() {
        // get web driver
        WebDriver webDriver = WebDriverFactory.getInstanceFor(webDriverManager);
        assertThat(webDriver)
                .as("WebDriver")
                .isNotNull();

        // set web driver
        setDriver(webDriver);

        webDriver.manage().window().maximize();
    }

    /**
     * Launch the application.
     */
    protected void launchApplication() {
        // launch the application using HTTP GET
        getDriver().get(System.getProperty(SERVER_PORT_PROPERTY, SERVER_PORT_DEFAULT));
    }

    /**
     * Open a web browser corresponding to the web driver and launch the application.
     */
    protected void setup() {
        // open web browser
        openWebBrowser();

        // launch application
        launchApplication();
    }

    /**
     * Close the web browser and invalidate its web driver.
     */
    protected void closeWebBrowser() {
        getDriver().quit();
    }

    /**
     * Close the web browser and invalidate its web driver.
     */
    protected void teardown() {
        // close the browser and invalidate its web driver
        closeWebBrowser();
    }

}
