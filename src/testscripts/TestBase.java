package testscripts;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.PredefinedActions;
import pages.DashBoardPage;
import util.PropertyFileOperation;

public class TestBase {
	private DashBoardPage dashBoardPage;
		
	@BeforeMethod
	public void setUp() {
		PredefinedActions.initializeBrowser("https://www.snapdeal.com/");	
	}
	//method ka return type page ka object h
	DashBoardPage getDashBoardPage(){
		if(dashBoardPage==null) 
			dashBoardPage= DashBoardPage.getDashBoardPage();
		return dashBoardPage;
		
	}
	
	ArrayList<String> readCredentials() {
		//one method can not return two values.means we have to return in the form of array.
		//normal array or use any class from collections or create two different methods.
		//use of hashmap is always better.key value ki pairing is better option.
		//agar array list use krte to 0th index pe username,1st pe password and retrieve krte waqt bhi vaise hi karna padta.return type arraylist
		
		PropertyFileOperation propOperation;
		ArrayList<String> credentialList = new ArrayList<String>();
		try {
		 propOperation = new PropertyFileOperation(".//resources//config//Credentials.properties");
		 credentialList.add(propOperation.getValue("fbUserName"));
		 credentialList.add(propOperation.getValue("fbpassword"));
		// propOperation.getValue("fbUserName");
		 //propOperation.getValue("fbpassword");
		 
		 	
		} catch (IOException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		return credentialList;	
	}
	
	@AfterMethod
	public void tearDown() {
		PredefinedActions.closeBrowser();
	}
		
		
	

}
