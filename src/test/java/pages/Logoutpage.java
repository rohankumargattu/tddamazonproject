package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Logoutpage 
{
	//Declare driver object
	public RemoteWebDriver driver;
	
	@FindBy(how=How.XPATH,using="//*[contains(text(),'Hello')]")
	public WebElement account;
	
	@FindBy(how=How.XPATH,using="//*[text()='Sign Out']")
	public WebElement signout;
	
	public Logoutpage(RemoteWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void clickSignout()
	{
		signout.click();
	}
	
	public void moveToSignin()
	{
		Actions a=new Actions(driver);
		a.moveToElement(account).build().perform();	
	}
}
