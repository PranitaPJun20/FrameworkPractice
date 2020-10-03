package base;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PredefinedActions {
	static WebDriver driver;
    private static WebDriverWait wait;
	public static void initializeBrowser(String url) {

		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : " + os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		wait = new WebDriverWait(driver,60000);
	}

	protected void hoverToElement(WebElement target) {
		Actions action = new Actions(driver);
		action.moveToElement(target).perform();
	}
	private String getlocatorType(String locator) {//[Xpath]:-//span[text()='Sign In']
		return locator.split("]:-")[0].substring(1);//Xpath	
	}
	
	private String getlocatorValue(String locator) {
		return locator.split("]:-")[1];////span[text()='Sign In'] 
	}
	
	private By getByReference(String locatorType,String locatorValue) {
		
		switch (locatorType.toUpperCase()) {
		case "XPATH":
				return By.xpath(locatorValue);

		case "ID":
			return By.id(locatorValue);
			
		// write for all 8 locators
		default:
			// throw new custom exception for invalid locator
			System.out.println("Invalid Locator");
		}
		return null;
	}
		
	
	protected WebElement getElement(String locator, boolean isWaitRequired) {
		String locatorType=getlocatorType(locator);
		String locatorValue = getlocatorValue(locator);
		//System.out.println("----"+locatorType);//used for debugging
		//System.out.println("----"+locatorValue);//used for debugging
		
		WebElement element = null;// initialize the variable
		//WebDriverWait wait = new WebDriverWait(driver, 60000);

		switch (locatorType.toUpperCase()) {
		case "XPATH":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			} else {
				element = driver.findElement(By.xpath(locatorValue));
			}
			break;

		case "ID":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			else
				element = driver.findElement(By.id(locatorValue));
			break;
		// write for all 8 locators
		default:
			// throw new custom exception for invalid locator
			System.out.println("Invalid Locator");
		}
		return element;
	}

	protected void switchToFrameByElement(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}

	protected void switchToFrameByElement(String locator, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		// driver.switchTo().frame(element); instead of writing this use above method
		// for switching.reusability of code
		switchToFrameByElement(element);
	}

	protected String getMainWindowHandleID() {
		return driver.getWindowHandle();
	}

	protected Set<String> getAllWindowHandleID() {
		return driver.getWindowHandles();
	}

	protected void switchToWindow(String windowID) {
		driver.switchTo().window(windowID);
	}

	protected void clickOnElement(WebElement element) {
		element.click();
	}

	protected void clickOnElement(String locator, boolean isWaitRequired) {
		WebElement element = getElement(locator , isWaitRequired);
		//WebDriverWait wait = new WebDriverWait(driver, 60000);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	protected void enterText(WebElement element,String textToBeEnter) {
		if(element.isEnabled())
			element.sendKeys(textToBeEnter);
		else System.out.println("Element is not enabled   ");
		
	}
	
	protected void enterText(String locator, boolean isWaitRequired,String textToBeEnter) {
		WebElement element = getElement(locator, isWaitRequired);
		enterText(element,textToBeEnter);
	}
	
	protected boolean waitForElementTextToBe(String locator,String expectedText ,boolean isWaitRequired) {
		
		//	WebElement signupUserElement = getElement(locator,isWaitRequired);
		boolean flag = wait.until(ExpectedConditions.textToBe(getByReference(getlocatorType(locator), getlocatorValue(locator)),expectedText));
		
		return flag;
		//return wait.until(ExpectedConditions.textToBePresentInElement(getElement(locator,isWaitRequired),expectedText));
		//textToBePresentInElemen THIS METHOD IS NO WORKING HERE.HENCE USE DIFFERENTS
	}
	
	protected WebElement waitForElementToBePresent(String locator) {
		return 	wait.until(ExpectedConditions.presenceOfElementLocated(getByReference(getlocatorType(locator), getlocatorValue(locator))));
				
	}
	public static void closeBrowser() {
		driver.close();
	}

}
