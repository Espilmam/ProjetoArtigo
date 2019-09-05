package tests;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TesteElemento {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette",true);
        driver = new FirefoxDriver();
        driver.get("https://outlook.live.com/owa/");
        driver.manage().window().maximize();
    }

    StopWatch timer = new StopWatch();

    @Test
    public void teste (){
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.start();
        driver.findElement(By.xpath("//span[contains(.,'Suporte')]"));
        timer.stop();
        System.out.println(timer.getTime());

        timer.reset();

        timer.start();
        driver.findElement(By.id("security"));
        timer.stop();
        System.out.println(timer.getTime());

        timer.reset();

        timer.start();
        driver.findElement(By.name("productivity-apps"));
        timer.stop();
        System.out.println(timer.getTime());


        timer.reset();

        timer.start();
        driver.findElement(By.className("masthead"));
        timer.stop();
        System.out.println(timer.getTime());
        timer.reset();

        timer.start();
        driver.findElement(By.linkText("Suporte"));
        timer.stop();
        System.out.println(timer.getTime());
    }


    @AfterMethod
    public void quit(){
        driver.quit();
    }

}
