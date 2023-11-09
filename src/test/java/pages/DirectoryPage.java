package pages;

import com.org.baseclass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class DirectoryPage extends BaseClass {
    protected static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    protected static final FluentWait fluentwait = new FluentWait(driver)
            .withTimeout(Duration.ofSeconds(15))
            .pollingEvery(Duration.ofSeconds(10))
            .ignoring(Exception.class);

    @FindBy(xpath = "//a[normalize-space()='Directory']")
    WebElement directory;

    @FindBy(xpath = "//h5[normalize-space()='Directory']")
    WebElement diretorytitlePage;
    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement typeName;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement searchBtn;
    @FindBy(xpath = "//div[3]//div[1]//div[2]//div[1]//div[1]//div[2]//i[1]")
    WebElement locationlink;
    @FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon'])[1]")
    WebElement loggedUserIcon;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement logOutLink;

    //create construction and initiate
    public DirectoryPage() throws IOException {
        super();
        PageFactory.initElements(driver, this);
    }


    //create a method to click directory link
    public DirectoryPage clickDirectoryPage() {
        directory.isDisplayed();
        return this;
    }

    //verity the directory page
    public boolean hasDirectoryText(){
        return diretorytitlePage.isDisplayed();
    }

    //create method for click on directory link
    public DirectoryPage clickDirectorylink() throws InterruptedException {
        directory.click();
        typeName.sendKeys("Ch");
        List<WebElement> list = driver.findElements(By.xpath("//div[@class=\"oxd-autocomplete-dropdown --positon-bottom\"]"));
        Thread.sleep(6000);
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role=\"listbox\"]")));
        System.out.println("Total name list : " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
            list.get(0).click();
            Thread.sleep(3000);
            break;
        }
        return  this;

    }

    public DirectoryPage locationSearch() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(locationlink));
        locationlink.click();
        List<WebElement> locationist = wait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".oxd-select-option span")));
        System.out.println("Total location list : " + locationist.size());
        locationist.get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        searchBtn.click();
        String myStr = "Ch";
        String a = String.valueOf(myStr.charAt(0)) + String.valueOf(myStr.charAt(1));

        List<WebElement> l = driver.findElements(
                By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-directory-card-header --break-words']"));
        // verify list size
        if (l.size() > 0) {
            System.out.println("Text: " + a + " is present. ");
        } else {
            System.out.println("Text: " + a + " is not present. ");
        }
        return this;
    }


    //logOut Method
    public LoginPage logOutLink() throws InterruptedException, IOException {
        loggedUserIcon.click();
        Thread.sleep(3000);
        logOutLink.click();
        return new LoginPage();

    }

}