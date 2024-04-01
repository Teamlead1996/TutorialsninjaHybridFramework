package Testcripts;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Tutorialsninja.Base.Base;
import com.Tutorialsninja.Base.Constants;
import com.Tutorialsninja.Base.FileUtil;
import com.Tutorialsninja.Base.JavaUtil;
import com.Tutorialsninja.ObjectRepo.LoginPage;

//Comments Added in loginTest.java
//@Listeners(com.Tutorialsninja.Base.Listeners.class)
public class LoginTest extends Base {
	LoginPage lp;
	FileUtil fu = new FileUtil();
	JavaUtil ju = new JavaUtil();

	@Test(priority =1)
	public void loginWithoutCredentials() throws Throwable {
		lp = new LoginPage(driver);
		lp.loginWithoutDetails();
		Assert.assertEquals(lp.getWarningMessage().getText(), Constants.invalidCredentialsWarningMessage);
	}

	@Test(priority = 3)
	public void loginWithValidCredentials() {
		lp = new LoginPage(driver);
		try {
			lp.loginWithValidDetails(fu.readDataFromPropertiesFile("userEmail"),
					fu.readDataFromPropertiesFile("password"));
		} catch (Throwable e) {

			e.printStackTrace();
		}
		Assert.assertEquals(driver.getTitle(), Constants.myAccountPageTitle);
	}

	@Test(priority = 2)
	public void loginWithInvalidCredentials() {
		lp=new LoginPage(driver);
		lp.loginWithInValidDetails(ju.getEmailAddress(), ju.getPassword());
		Assert.assertEquals(lp.getWarningMessage().getText(), Constants.invalidCredentialsWarningMessage);
	}
}
