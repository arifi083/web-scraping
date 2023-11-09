package pages;

import com.org.baseclass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class HomePage extends BaseClass {
    protected static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    protected static final FluentWait fluentwait = new FluentWait(driver)
            .withTimeout(Duration.ofSeconds(60))
            .pollingEvery(Duration.ofMillis(200))
            .ignoring(Exception.class);
    @FindBy(xpath="(//p[normalize-space()='My Actions'])[1]")
    WebElement dashboardVerifyText;

    //create constructor of HomePage
    public HomePage() throws IOException {
        super();
        PageFactory.initElements(driver, this);
    }

    //assert dashboard
    public boolean dashboardTextVerify(){
        try {
            fluentwait.until(ExpectedConditions.visibilityOf(dashboardVerifyText));
            return true;
        } catch (Exception e) {
            return false;
        }

    }


}
