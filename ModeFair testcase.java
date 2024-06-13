import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CurrencyConverterTest {
    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Test Case 1: Verify USD to MYR conversion
        driver.get("https://www.xe.com/");
        driver.findElement(By.id("amount")).sendKeys("1.00");
        new Select(driver.findElement(By.id("fromCurrency"))).selectByVisibleText("USD - US Dollar");
        new Select(driver.findElement(By.id("toCurrency"))).selectByVisibleText("MYR - Malaysian Ringgit");
        driver.findElement(By.id("convertButton")).click();
        WebElement result = driver.findElement(By.id("conversionResult"));
        System.out.println("USD to MYR conversion: " + result.getText());

        // Test Case 2: Verify empty amount field
        driver.get("https://www.xe.com/");
        new Select(driver.findElement(By.id("fromCurrency"))).selectByVisibleText("USD - US Dollar");
        new Select(driver.findElement(By.id("toCurrency"))).selectByVisibleText("MYR - Malaysian Ringgit");
        driver.findElement(By.id("convertButton")).click();
        WebElement errorMessage = driver.findElement(By.id("errorMessage"));
        System.out.println("Empty amount field error: " + errorMessage.getText());

        // Test Case 3: Verify negative amount conversion
        driver.get("https://www.xe.com/");
        driver.findElement(By.id("amount")).sendKeys("-1.00");
        new Select(driver.findElement(By.id("fromCurrency"))).selectByVisibleText("USD - US Dollar");
        new Select(driver.findElement(By.id("toCurrency"))).selectByVisibleText("MYR - Malaysian Ringgit");
        driver.findElement(By.id("convertButton")).click();
        errorMessage = driver.findElement(By.id("errorMessage"));
        System.out.println("Negative amount error: " + errorMessage.getText());

        // Test Case 4: Verify non-numeric amount input
        driver.get("https://www.xe.com/");
        driver.findElement(By.id("amount")).sendKeys("abc");
        new Select(driver.findElement(By.id("fromCurrency"))).selectByVisibleText("USD - US Dollar");
        new Select(driver.findElement(By.id("toCurrency"))).selectByVisibleText("MYR - Malaysian Ringgit");
        driver.findElement(By.id("convertButton")).click();
        errorMessage = driver.findElement(By.id("errorMessage"));
        System.out.println("Non-numeric amount error: " + errorMessage.getText());

        // Test Case 5: Verify conversion between different currencies (USD to EUR)
        driver.get("https://www.xe.com/");
        driver.findElement(By.id("amount")).sendKeys("1.00");
        new Select(driver.findElement(By.id("fromCurrency"))).selectByVisibleText("USD - US Dollar");
        new Select(driver.findElement(By.id("toCurrency"))).selectByVisibleText("EUR - Euro");
        driver.findElement(By.id("convertButton")).click();
        result = driver.findElement(By.id("conversionResult"));
        System.out.println("USD to EUR conversion: " + result.getText());

        // Close the browser
        driver.quit();
    }
}
