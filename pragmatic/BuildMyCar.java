package bg.pragmatic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.*;
import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class BuildMyCar {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Eli\\drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://pragmatic.bg/automation/lecture13/Config.html");
    }

    @AfterClass
    public void tearDown() {
          driver.quit();
    }
    /*
 /**
    * This is a test that check AirBags
    */
  /*  @Test
    public void testAirbags() {
        WebElement elementAirbags = driver.findElement(By.name("airbags"));
        String valueUsername= elementAirbags.getAttribute("value");
        Assert.assertEquals("Airbags", valueUsername);
    }
     /**
    * This is a test that check Parking sensors
    */
   /* @Test
    public void testParksensor() {
        WebElement elementParksensor= driver.findElement(By.name("parksensor"));
        elementParksensor.click();
        String valueUsername= elementParksensor.getAttribute("value");
        Assert.assertEquals("ParkingSensor", valueUsername);
    } */
    /**
     * This is a test that check AirBags by Action
     */
    @Test
    public void testCheckAirbags() {
        driver.get("http://pragmatic.bg/automation/lecture13/Config.html");
                WebElement checkBoxAirbags = driver.findElement(By.name("airbags"));
        Actions builder = new Actions(driver);
        builder.click(checkBoxAirbags).perform();
        assertTrue(checkBoxAirbags.isSelected());
    }
    /**
     * This is a test that check Parking Sensor by Action
     */
    @Test
    public void testCheckParkingsensor() {
        driver.get("http://pragmatic.bg/automation/lecture13/Config.html");
        WebElement checkBoxParkingSensor = driver.findElement(By.name("parksensor"));
        Actions builder = new Actions(driver);
        builder.click(checkBoxParkingSensor).perform();
        assertTrue(checkBoxParkingSensor.isSelected());
    }
}
