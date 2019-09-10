package tests;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class TesteElemento {
    WebDriver driver;
    String navegador = "firefox";
    DesiredCapabilities capabilities;

    public void setUp(String url){
        if (navegador.contains("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
            FirefoxBinary firefoxBinary = new FirefoxBinary();
            firefoxBinary.addCommandLineOptions("--headless");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setBinary(firefoxBinary);
            capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("marionette", true);
            driver = new FirefoxDriver();
            driver.get(url);
            driver.manage().window().maximize();
        } else {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            options.addArguments("--disable-gpu --proxy-bypass-list=* --proxy-server='direct://'");
            capabilities = DesiredCapabilities.chrome();
            driver = new ChromeDriver(options);
            driver.get(url);
            driver.manage().window().maximize();
        }
    }

    StopWatch timerName = new StopWatch();
    StopWatch timerID = new StopWatch();
    StopWatch timerClassname = new StopWatch();
    StopWatch timerClassname2 = new StopWatch();
    StopWatch timerXpath = new StopWatch();
    StopWatch timerLinkText = new StopWatch();
    StopWatch timerLPartialinkText = new StopWatch();
    StopWatch timerCss = new StopWatch();


    @Test
    public void testeOutlook (){
        setUp("https://outlook.live.com/owa/");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long id = 0;
        long xpath = 0;
        long name = 0;
        long classname = 0;
        long classname2 = 0;
        long linktext = 0;
        long partialLinktext = 0;
        long css = 0;

        for (int cont = 0; cont < 1000; cont ++) {
            timerXpath.start();
            driver.findElement(By.xpath("//span[contains(.,'Suporte')]"));
            xpath += timerXpath.getTime();
            timerXpath.reset();

            timerID.start();
            driver.findElement(By.id("security"));
            id += timerID.getTime();
            timerID.reset();

            timerName.start();
            driver.findElement(By.name("productivity-apps"));
            name += timerName.getTime();
            timerName.reset();

            timerClassname.start();
            driver.findElement(By.className("masthead"));
            classname += timerClassname.getTime();
            timerClassname.reset();

            timerClassname2.start();
            driver.findElement(By.className("tagline"));
            classname2 += timerClassname2.getTime();
            timerClassname2.reset();

            timerLinkText.start();
            driver.findElement(By.linkText("Suporte"));
            linktext += timerLinkText.getTime();
            timerLinkText.reset();

            timerLPartialinkText.start();
            driver.findElement(By.partialLinkText("Suporte"));
            partialLinktext += timerLPartialinkText.getTime();
            timerLPartialinkText.reset();

            timerCss.start();
            driver.findElement(By.cssSelector(".masthead-shell .tagline"));
            css += timerCss.getTime();
            timerCss.reset();
        }
        System.out.println(navegador);

        System.out.println(id + " - ID");
        System.out.println(name + " - Name");
        System.out.println(linktext + " - LinkText");
        System.out.println(partialLinktext + " - Partial LinkText");
        System.out.println(classname + " - Classname");
        System.out.println(classname2 + " - Classname2");
        System.out.println(xpath + " - X-Path");
        System.out.println(css + " - Css Selector");
    }

    @Test
    public void testeW3c () {
        String url = "https://www.w3.org/";


    }

    @AfterMethod
    public void quit(){
        /*File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            // now copy the  screenshot to desired location using copyFile //method
            FileUtils.copyFile(src, new File("target\\evidencia\\imagem.jpg"));
        } catch (IOException e){

        }*/
        driver.quit();
    }

}
