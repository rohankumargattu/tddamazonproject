package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Loginpage
{
	//Declare driver object
	public RemoteWebDriver driver;
	
	@FindBy(how=How.NAME,using="email")
	public WebElement emailid;
	
	@FindBy(how=How.XPATH,using="(//*[@id='continue'])[1]")
	public WebElement continuebtn;
	
	@FindBy(how=How.XPATH,using="//*[contains(text(),'Enter your email or mobile phone number')]")
	public WebElement blankeorpinputerr;
	
	@FindBy(how=How.XPATH,using="//*[contains(text(),'We cannot find an account with that email address')]")
	public WebElement invalidemailiderr;
	
	@FindBy(how=How.XPATH,using="//*[contains(text(),'We cannot find an account with that mobile number')]")
	public WebElement invalidmbnoerr;
	
	public Loginpage(RemoteWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void fillEmailID(String x)
	{
		emailid.sendKeys(x);
	}
	
	public void clickContinueBtn()
	{
		continuebtn.click();
	}
}
