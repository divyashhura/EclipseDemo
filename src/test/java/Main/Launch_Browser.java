package Main;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class Launch_Browser {
    public static WebDriver driver;
    String actualTitle;

    @BeforeSuite
    public void LaunchBrowser()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        //Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        //Deleting Cookies
        driver.manage().deleteAllCookies();

        //Maximize browser window
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }
    @Test
    public void Login()
    {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
    }
    @Test
    public void VerifyPage()
    {
        actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle, "OrangeHRM");
    }
    @Test
    public void Logout()
    {
        driver.findElement(By.partialLinkText("Welcome")).click();
        driver.findElement(By.linkText("Logout")).click();
    }
    @AfterSuite
    public void Close_Browser()
    {
//        extent.flush();
        driver.close();
    }
}