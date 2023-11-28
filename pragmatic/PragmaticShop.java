package bg.pragmatic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;


public class PragmaticShop {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Eli\\drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://shop.pragmatic.bg/admin/");
    }

    @AfterClass
    public void tearDown() {
       driver.quit();
    }
    /**
     * This is a test that verify login with correct username and password
     */
    @Test
    public void testLogin() {
        WebElement elementUsername = driver.findElement(By.id("input-username"));
        elementUsername.click();
        elementUsername.sendKeys("admin");
        String valueUsername= elementUsername.getAttribute("value");
        Assert.assertEquals("admin", valueUsername);

        WebElement elementPassword = driver.findElement(By.id("input-password"));
        elementPassword.click();
        elementPassword.sendKeys("parola123!");
        String valuePassword = elementPassword.getAttribute("value");
        Assert.assertEquals("parola123!", valuePassword);

        WebElement elementLoginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        elementLoginButton.click();
    }
    /**
     * This is a test that checks the elements of Order menu
     */
    @Test
    public void odrerElemnts() {
        Select make = new Select(driver.findElement(By.name("filter_order_status_id")));
        assertFalse(make.isMultiple());
        assertEquals(make.getOptions().size(), 4);
        List<String> exp_options = Arrays.asList(new String[]{"Missing Orders", "Canceled","Canceled Reversal",
                "Chargeback","Coplete","Denied","Expired","Failed","Pending","Processed","Processing","Refunded","Reversed","Shipped","Voided"});
        List<String> act_options = new ArrayList<String>();
        for(WebElement option : make.getOptions())
            act_options.add(option.getText());
        assertArrayEquals(act_options.toArray(), exp_options.toArray());
        make.selectByVisibleText("Missing Orders");
        assertEquals(make.getFirstSelectedOption().getText(), "Missing Orders");
        make.selectByValue("Canceled");
        assertEquals(make.getFirstSelectedOption().getText(), "Canceled");
//or we can select an option in Dropdown using index
        make.selectByIndex(0);
        assertEquals(make.getFirstSelectedOption().getText(), "Canceled Reversal");
    }
}
