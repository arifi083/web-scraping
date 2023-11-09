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
import java.util.List;

public class LoginPage extends BaseClass {
    protected static final FluentWait fluentwait = new FluentWait(driver)
            .withTimeout(Duration.ofSeconds(60))
            .pollingEvery(Duration.ofMillis(200))
            .ignoring(Exception.class);
    protected static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

    @FindBy(xpath="//input[@name=\"username\"]")
    WebElement hrmUserName;

    @FindBy(xpath="//input[@name=\"password\"]")
    WebElement hrmPassword;
    @FindBy(css ="button[type=\"submit\"]")
    WebElement loginBtn;

    @FindBy(css="span[class*='error']")
    List<WebElement> errrs;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
    WebElement blankCredsVerify;
    @FindBy(xpath="(//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'])[1]")
    WebElement partialCreds;

    //create constructor
    public LoginPage() throws IOException {
        super();
        PageFactory.initElements(driver,this);

    }

    //create a method to execute login panel
    public HomePage login(String username, String password) throws IOException, InterruptedException {
        hrmUserName.isDisplayed();
        hrmUserName.sendKeys(username);
        hrmPassword.isDisplayed();
        hrmPassword.sendKeys(password);
        loginBtn.click();
       // Thread.sleep(12000);
        return new HomePage();

    }

    //negative testing
    public LoginPage blanklogin(String userName, String Password) throws IOException, InterruptedException {
        hrmUserName.isDisplayed();
        hrmUserName.sendKeys(userName);
        hrmPassword.isDisplayed();
        hrmPassword.sendKeys(Password);
        loginBtn.click();
        return this;

    }

    //write method for errorAssertion

    public boolean verifyCreds(){
        try {
            fluentwait.until(ExpectedConditions.visibilityOf(blankCredsVerify));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    //negative testing partial valid
    public LoginPage partialValidCreds(String userName) throws IOException, InterruptedException {
        hrmUserName.isDisplayed();
        hrmUserName.sendKeys(userName);
        loginBtn.click();
        Thread.sleep(8000);
        return this;

    }
    public boolean verifyPartialCreds(){
        try {
            fluentwait.until(ExpectedConditions.visibilityOf(partialCreds));
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
