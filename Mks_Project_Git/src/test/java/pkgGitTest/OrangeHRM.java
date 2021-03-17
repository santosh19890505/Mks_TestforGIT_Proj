package pkgGitTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class OrangeHRM {
	
	WebDriver driver;
	
	
  @Test
  public void verifyTitle() 
  {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium_Latest\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
	//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(driver.getTitle().contains("OrangeHRM"))
		{
			System.out.println("Test1 is verified");
		}
		else
		{
			System.out.println("Test1 asertion failed");
		}
  }
  
  @Test(dependsOnMethods = {"verifyTitle"})
  public void loginApplication() 
  {
	driver.findElement(By.id("txtUsername")).sendKeys("Admin");
	driver.findElement(By.id("txtPassword")).sendKeys("admin123");
	driver.findElement(By.id("btnLogin")).click();
  }
  
  @Test(dependsOnMethods = {"loginApplication"})
  public void verifyAfterLogin()
  {
	  if(driver.getCurrentUrl().contains("dashboard"))
	  {
		  System.out.println("Test3 verfified");
	  }
	  else
	  {
		  System.out.println("Test3 failed");
	  }
  }
  
  @Test(dependsOnMethods = {"verifyAfterLogin"})
  public void logoutFromApp()
  {
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  driver.findElement(By.xpath("//a[text()='Welcome Paul']")).click();
	  
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=\"Logout\"]")));
	  
	  driver.findElement(By.xpath("//a[text()=\"Logout\"]")).click();
  }
  
  @Test(dependsOnMethods = {"logoutFromApp"})
  public void verifyAfterLogout() throws InterruptedException
  {
	  if(driver.getCurrentUrl().contains("login"))
	  {
		  System.out.println("Test5 is verified");
	  }
	  else
	  {
		  System.out.println("Test5 is failed");
	  }
	  
	  Thread.sleep(5000);
	  driver.quit();
  }
  

  
}
