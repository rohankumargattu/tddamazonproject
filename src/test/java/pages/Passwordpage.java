package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class Passwordpage 
{
	//Declare driver object
	public RemoteWebDriver driver;
	Actions a;
	
	@FindBy(how=How.NAME,using="password")
	public WebElement pwd;
	
	@FindBy(how=How.XPATH,using="(//*[contains(text(),'Login')])[2]")
	public WebElement loginbtn;
	
	@FindBy(how=How.XPATH,using="//*[contains(text(),'Enter your password')]")
	public WebElement blankpwderr;
	
	@FindBy(how=How.XPATH,using="//*[contains(text(),'Your password is incorrect')]")
	public WebElement invalidpwderr;
	
	public Passwordpage(RemoteWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void fillPWD(String x)
	{
		pwd.sendKeys(x);
	}
	
	public void clickLoginBtn()
	{
		a=new Actions(driver);
		a.click(loginbtn).build().perform();
	}
}
