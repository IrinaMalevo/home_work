import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TvNetCommentsTest {
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By HOME_PAGE_ARTICLE = By.tagName("article"); // Vse statji na tvnet
    private final By HOME_PAGE_TITLE = By.xpath(".//span[@class = 'list-article__headline']");
    private final By HOME_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'list-article__comment')]");
    //Lokatori dlja statji na delfi
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'article-headline')]");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//span[contains(@class, 'article-share__item--count')]");

    //private final By ARTICLE_PAGE_INNER_COMMENT = By.xpath(".//a[contains(@class, 'text-red-ribbon')]");
    //private final By COMMENTS = By.xpath(".//span[@class = 'type-cnt']");
    //private final By INNER_TITLE = By.xpath(".//h1[@class = 'article-title']");


    //DRIVER - DLJA @AFTER EACH v samom nizu, tam 2 drajvera tak kak, ctobi prenadlezala vsem metodam v predelax vsej AREA
    private WebDriver driver;

    @Test
    public void titleAndCommentsCountCheck() {

        System.setProperty("webdriver.chrome.driver", "/Users/anna/Desktop/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://tvnet.lv");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(ACCEPT_COOKIES_BTN));
        driver.findElement(ACCEPT_COOKIES_BTN).click();

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,950)");
        //---------HOME PAGE------------
        List<WebElement> articles = driver.findElements(HOME_PAGE_ARTICLE); //1. sobrtaj vse statji celikom v spisok
        WebElement article = articles.get(2); // 2. berem otdeljnuju vzjatuju statju

        String homePageTitle = article.findElement(HOME_PAGE_TITLE).getText().replaceAll("\\(\\d+\\)$", "").trim(); // .trim() - izbavitsja ot pustoj stroki v zagalovke//3. soxronitj zagolok statji i kommentarii
        int homePageCommentsCount = getCommentsCount(article, HOME_PAGE_COMMENTS);

        // 10. Perexod na stranicu statji
        article.findElement(HOME_PAGE_TITLE).click();

        //-------------ARTICLE PAGE----------------
        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText().trim(); //11. Poluchaem Zagolovok na samoj stranice statji
        int articlePageCommentsCount = getCommentsCount(ARTICLE_PAGE_COMMENTS); // Poluchaem kolichestvo kommentov



        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title :(");
        //homepagetitle - expected result
        //articlePage title - actual result
        //Wrong titles- comment
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");
        //--------------COMMENTS PAGE------------------
        // List<WebElement> commentCounts = driver.findElements(COMMENTS);
        // int articlePageInnerCommentsCount = removeBrackets(commentCounts.get(0)) + removeBrackets(commentCounts.get(1));

        //driver.findElement(ARTICLE_PAGE_INNER_COMMENT).click();

        // String articlePageInnerTitle = driver.findElement(INNER_TITLE).getText().trim();

        //proverki testov-------------------------------------------
        //Proverki testov assertion i assumption, esli ne vipolnjajutsja, to ostanavlivaetsja assertion i assumprion pojdet daljwe

        //Assertions.assertEquals(articlePageCommentsCount, articlePageInnerCommentsCount, "Wohoo :(");
        // Assertions.assertEquals(articlePageTitle, articlePageInnerTitle, "Hehe :(");
    }

    // 12. otdeljnij metod izbovlenija ot skobok
    private int getCommentsCount(By locator) { //int - cto vozvrawaet funkcija
        int commentsCount = 0; // esli kommentov net, to budet nolj
        if (!driver.findElements(locator).isEmpty()) {
           // commentsCount = removeBrackets(driver.findElement(locator));

        }
        return commentsCount;
    }


    private int getCommentsCount(WebElement we, By locator) {
        int commentsCount = 0; // esli kommentov net, to budet nolj
        if (!we.findElements(locator).isEmpty()) {
           // commentsCount = removeBrackets(we.findElement(locator));
        }
        return commentsCount; // return - vernutj kak rezuljtat raboti metoda
    }

    //private int removeBrackets(WebElement we) {
      //  String commentsCountText = we.getText();
        //commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1); // (36) v 36
        //return Integer.parseInt(commentsCountText); // 36 (Strings) --> 36 (int)
   // }
}




