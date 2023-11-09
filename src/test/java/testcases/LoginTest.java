package testcases;

import Utils.TimeOut;
import com.org.baseclass.BaseClass;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;

public class LoginTest extends BaseClass {
    LoginPage loginPage;
    HomePage homepage;
    //create constructor for LoginTest

    public LoginTest() throws IOException {
        super();
    }

    @BeforeMethod
    public void initialization() throws IOException {
        setUp();
        loginPage=new LoginPage();
    }

    @Test(priority = 3)
    public void loginWithValidCredentials() throws IOException, InterruptedException {

     homepage =new LoginPage()
             .login(getUserName(),getpassword());
     //assert dashboard
        Assert.assertTrue(homepage.dashboardTextVerify());


    }

    //negative testing -2
    @Test(priority = 2)
    public void partialValidCredentials() throws IOException, InterruptedException {

        loginPage= loginPage
                .partialValidCreds(getUserName());
        Assert.assertTrue(loginPage.verifyPartialCreds());



    }
    //negative testing -1
//    @Test(priority = 1)
//    public void loginWithInValidCredentials() throws IOException, InterruptedException {
//
//        loginPage= loginPage
//               .blanklogin(LoremIpsum.getInstance().getFirstName(),getpassword());
//
//        Assert.assertTrue(loginPage.verifyCreds());
//
//    }
    @Test(dataProvider = "QAOPS-BATCH3-DataProvider")
    public void loginWithInValidCredentials(String userName, String password) throws IOException, InterruptedException {

        loginPage= loginPage
                .blanklogin(userName,password);

        Assert.assertTrue(loginPage.verifyCreds());

    }

//    @Test(dataProvider = "getDataProviderData")
//    public void loginWithInValidCredentials(String userName, String password) throws IOException, InterruptedException {
//
//        loginPage= loginPage
//                .blanklogin(userName,password);
//
//        Assert.assertTrue(loginPage.verifyCreds());
//
//    }
//    @DataProvider
//    public Object[][] getDataProviderData(){
//        return new Object[][]{{LoremIpsum.getInstance().getFirstName(),getpassword()}};
//
//    }
//
    @DataProvider(name="QAOPS-BATCH3-DataProvider")
    public Object[][] getDataProviderDataFromExcel(){
        return TimeOut.getTestData("Sheet1");

    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
