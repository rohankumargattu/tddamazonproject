package tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Homepage;
import pages.Loginpage;
import pages.Logoutpage;
import pages.Passwordpage;

public class AmazonLoginTest
{
	//Declare Global objects
	RemoteWebDriver driver;
	WebDriverWait wait;
	Homepage hp;
	Loginpage lginp;
	Logoutpage lgoutp;
	Passwordpage pp;
	
	@Test(priority=1)
	public void launchSite()
	{
		//Launch browser
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.silentOutput","true");
		driver=new ChromeDriver();
		//launch site and maximize
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		wait=new WebDriverWait(driver,20);
		//Create page class objects
		hp=new Homepage(driver);
		lginp=new Loginpage(driver);
		lgoutp=new Logoutpage(driver);
		pp=new Passwordpage(driver);
		wait.until(ExpectedConditions.visibilityOf(hp.signin));
	}
	
	@Test(priority=2)
	@Parameters({"emailid","emailidc","pwd","pwdc"})
	public void loginOperation(String e,String ec,String p,String pc) throws Exception
	{
		hp.clickSignin();
		wait.until(ExpectedConditions.visibilityOf(lginp.emailid));
		lginp.fillEmailID(e);
		wait.until(ExpectedConditions.elementToBeClickable(lginp.continuebtn));
		lginp.clickContinueBtn();
		Thread.sleep(3000);
		//EmailID Validations
		try
		{
			if(e.length()==0 && lginp.blankeorpinputerr.isDisplayed())
			{
				Reporter.log("Blank email/phone test passed");
				Assert.assertTrue(true);
			}
			else if(ec.equalsIgnoreCase("invalid_emailid") && lginp.invalidemailiderr.isDisplayed())
			{
				Reporter.log("Invalid email test passed");
				Assert.assertTrue(true);
			}
			else if(ec.equalsIgnoreCase("invalid_mbno") && lginp.invalidmbnoerr.isDisplayed())
			{
				Reporter.log("Invalid mbno test passed");
				Assert.assertTrue(true);
			}
			else if(ec.equalsIgnoreCase("valid") && pp.pwd.isDisplayed())
			{
				Reporter.log("Valid email/phone test passed");
				Assert.assertTrue(true);
				//Pwd validations
				pp.fillPWD(p);
				wait.until(ExpectedConditions.elementToBeClickable(pp.loginbtn));
				pp.clickLoginBtn();
				Thread.sleep(5000);
				try
				{
					if(p.length()==0 && pp.blankpwderr.isDisplayed())
					{
						Reporter.log("Blank Pwd test passed");
						Assert.assertTrue(true);
					}
					else if(pc.equalsIgnoreCase("invalid") && pp.invalidpwderr.isDisplayed())
					{
						Reporter.log("Invalid Pwd test passed");
						Assert.assertTrue(true);
					}
					else if(pc.equalsIgnoreCase("valid") && lgoutp.account.isDisplayed())
					{
						Reporter.log("Valid Pwd test passed");
						Assert.assertTrue(true);
						//Perform logout
						lgoutp.moveToSignin();
						wait.until(ExpectedConditions.visibilityOf(lgoutp.signout));
						lgoutp.clickSignout();
						wait.until(ExpectedConditions.visibilityOf(lginp.emailid));
					}
					else
					{
						SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
						Date dt=new Date();
						String ssname=sf.format(dt)+".png";
						File src=driver.getScreenshotAs(OutputType.FILE);
						File dest=new File(ssname);
						FileHandler.copy(src,dest);
						Reporter.log("Valid pwd test failed");
						String code="<img src=\"file:///"+dest.getAbsolutePath()+"\" alt=\"\"/>";
						//String code="<a href=\""+dest.getAbsolutePath()+"\"><img src=\""+dest.getAbsolutePath()+"\" height=\"\" width=\"\"/></a>";
						Reporter.log(code);
						Assert.assertTrue(false);
					}
				}
				catch(Exception exe)
				{
					Reporter.log(exe.getMessage());
					Assert.assertTrue(false);
				}
			}
			else
			{
				SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
				Date dt=new Date();
				String ssname=sf.format(dt)+".png";
				File src=driver.getScreenshotAs(OutputType.FILE);
				File dest=new File(ssname);
				FileHandler.copy(src,dest);
				Reporter.log("Valid pwd test failed");
				String code="<img src=\"file:///"+dest.getAbsolutePath()+"\" alt=\"\"/>";
				//String code="<a href=\""+dest.getAbsolutePath()+"\"><img src=\""+dest.getAbsolutePath()+"\" height=\"\" width=\"\"/></a>";
				Reporter.log(code);
				Assert.assertTrue(false);
			}
		}
		catch(Exception ex)
		{
			Reporter.log(ex.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=3)
	public void closeSite()
	{
		//Close site
		driver.close();
	}
	
	//Automating Results file
	//@AfterSuite
	public void openResults()
	{
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("E:\\Automation\\AutomationNested\\com.tddtestng.gui.facebook\\test-output\\index.html");
		driver.findElement(By.xpath("//*[text()='Reporter output']")).click();
	}
}
