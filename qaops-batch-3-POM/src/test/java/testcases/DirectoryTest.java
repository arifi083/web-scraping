package testcases;

import com.org.baseclass.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import pages.DirectoryPage;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;

public class DirectoryTest extends BaseClass {
    LoginPage loginPage;
    HomePage homepage;
    DirectoryPage dirtPage;

    //create constructor
    public DirectoryTest() throws IOException {
        super();
    }

    @BeforeMethod
    public void initialization() throws IOException, InterruptedException {
        setUp();
        homepage = new LoginPage()
                .login(getUserName(), getpassword());
        dirtPage = new DirectoryPage();
    }

    //Write Test Case
    public void verifyDirectoryPage() throws IOException, InterruptedException {
        dirtPage = dirtPage
                .clickDirectorylink();
        //assert
        Assert.assertTrue(dirtPage.hasDirectoryText());
       dirtPage=dirtPage
               .locationSearch();
        loginPage=dirtPage
                .logOutLink();
    }

}
