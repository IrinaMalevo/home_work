import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MyHomeWork {
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By OPEN_ARTICLE = By.className("list-article__headline");
    //private final By OPEN_COMMENT = By.xpath(".//a[@class='article-share__item article-share__item--comments article-share__item-with-count']");

    private final By ARTICLE_HEADLINE_PRINT = By.xpath(".//span[@class = 'list-article__headline']");

    private final By ALL_HEADLINE_ARTICLE = By.xpath(".//span[@class = 'list-article__headline']");
    private final By ALL_COMMENTS = By.xpath(".//span[@class = 'list-article__comment section-font-color']");
    private final By LOGO_TVNET = By.xpath(".//a[@class = 'flex header-logo flex--align-items-center']");
    private final By LANGUAGE_TVNET = By.xpath(".//a[text()='RUS']");

    private final By OPEN_ALL_ARTICLES = By.xpath(".//span[@class = 'list-article__headline']");
    private WebDriver driver;

    @Test
    public void firstTest() {
        System.setProperty("webdriver.chrome.driver", "/Users/anna/Desktop/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://tvnet.lv");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(ACCEPT_COOKIES_BTN));
        driver.findElement(ACCEPT_COOKIES_BTN).click();
        //browserWindow.findElement(OPEN_ARTICLE).click();
        //browserWindow.findElement(OPEN_COMMENT).click();
        //String heading = browserWindow.findElement(By.xpath("//span[@class = 'list-article__headline']")).getText();
        //System.out.println("My answer: "+heading);

        //String headline = browserWindow.findElement(ARTICLE_HEADLINE_PRINT).getText();
        //System.out.println(headline);

        //browserWindow.findElement(LANGUAGE_TVNET).click();


        List<WebElement> headlines = driver.findElements(OPEN_ALL_ARTICLES);
        for (int i = 0; i < headlines.size(); i++) {
        System.out.println("Article: " + headlines.get(i).getText().replaceAll("\\(\\d+\\)$", "").trim());
        }


        driver.close();
    }

}
