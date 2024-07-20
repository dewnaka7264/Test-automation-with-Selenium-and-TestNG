import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifySauceLabsWithTestNG {
    private static WebDriver driver;
    @BeforeClass
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/v1/");
    }

    @Test
    public static void loginTest() throws InterruptedException {
        WebElement userNameField= driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        userNameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='product_label']")).getText(),"Products" ,"test passed");

        /*if (driver.findElement(By.xpath("//div[@class='product_label']")).getText().contentEquals("Products Table"))
            System.out.println("test is passed");
        else {
            System.out.println("test is failed");
        }*/
    }

    @AfterClass
    public static void tearDown(){
        driver.close();
        driver.quit();
    }


}
