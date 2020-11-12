package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Homepage
{
	//Declare driver object
	public RemoteWebDriver driver;
	
	@FindBy(how=How.XPATH,using="(//*[text()='Hello, Sign in'])[1]")
	public WebElement signin;
	
	public Homepage(RemoteWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void clickSignin()
	{
		signin.click();
	}
}
