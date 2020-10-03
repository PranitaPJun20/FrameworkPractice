package pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebElement;



import base.PredefinedActions;
import util.PropertyFileOperation;

public class DashBoardPage extends PredefinedActions {
	//apply singleton design pattern
	private static DashBoardPage dashBoardPage;
	
	private PropertyFileOperation propOperation;
	
	private DashBoardPage() {
		try {
			propOperation = new PropertyFileOperation(".//resources//config//DashBoardPageProp.properties");
		} catch (IOException e) {
			System.out.println("DashboardPage Property file is not available");
			//custom exception----->unchecked
		}
		
	}
	
	static public DashBoardPage getDashBoardPage() {
		if(dashBoardPage==null)
			 dashBoardPage = new DashBoardPage();
		return dashBoardPage;
	} 
	
	public void signupUsingFB(String fbUsername,String fbPassword) throws InterruptedException {
	
	WebElement target = getElement(propOperation.getValue("SignInMenu"),false);
	hoverToElement(target);
	
	clickOnElement(propOperation.getValue("loginBtn"),true);
	
	System.out.println("Clicked on login.");
	
	switchToFrameByElement(propOperation.getValue("loginFrame"),true);
	
	System.out.println("Switched to frame.");
	clickOnElement(propOperation.getValue("fbUserLoginBtn"), true);
	System.out.println("Clicked on fb login.");
	
	Thread.sleep(2000);
	String mainWindow=getMainWindowHandleID();	
    // To handle all new opened window.				
    Set<String> s1=getAllWindowHandleID();		
    Iterator<String> i1=s1.iterator();		
    		
    while(i1.hasNext())			
    {		
        String ChildWindow=i1.next();		
        		
        if(!mainWindow.equalsIgnoreCase(ChildWindow))			
        {    		
        	System.out.println("Switch to window.");
                // Switching to Child window
        		switchToWindow(ChildWindow);
                Thread.sleep(2000);
                enterText(propOperation.getValue("fbUserEmailtxt"),true,fbUsername);
                enterText(propOperation.getValue("fbUserEmailPassword"),false,fbPassword);
                clickOnElement(propOperation.getValue("fbLoginBtn"),false);
        }		
    }	
    
    // Switching to Parent window i.e Main Window.  
    switchToWindow(mainWindow);
    
	}
       
	public boolean isUserNamePresent(String username) {
	return waitForElementTextToBe(propOperation.getValue("accountUserName"),username,true);
		
		/*int count=0;
		String SigninUsername="Sign In";
		while(count<15 && SigninUsername.equals("Sign In") ) {
			SigninUsername=getElement(propOperation.getValue("accountUserName"), true).getText();
			count++;
		} 
		return SigninUsername; */
	}

}



