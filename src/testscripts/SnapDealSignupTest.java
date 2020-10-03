package testscripts;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashBoardPage;

public class SnapDealSignupTest extends TestBase {
	@Test
	public void snapDealTest() throws InterruptedException {

		String expectedUserName="Pranita Puranik";
		//ham yaha object nahi banayenge..varna har test ke liye dashboard page ka object banana padega.ye kam ham TestBase me karenge
		DashBoardPage dashBoardPage = getDashBoardPage();
		System.out.println(dashBoardPage);//will print memory address
		ArrayList<String> credentialList = readCredentials();	//Accept  the function in arraylist
		dashBoardPage.signupUsingFB(credentialList.get(0),credentialList.get(1));//method call karke get uname and password at index  and 1
		boolean userNamePresentFlag = dashBoardPage.isUserNamePresent(expectedUserName);
		Assert.assertTrue(userNamePresentFlag);
	}
		
	
}
