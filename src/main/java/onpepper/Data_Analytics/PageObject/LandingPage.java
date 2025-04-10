package onpepper.Data_Analytics.PageObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import onpepper.Data_Analytics.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	public WebDriver driver;
	
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public HomePage goTo() throws IOException, InterruptedException {
		Properties prop=new Properties();
		FileInputStream fsi=new FileInputStream((System.getProperty("user.dir")+"//src//main//java//onpepper//Data_Analytics//resources//globaldata.properties"));
		prop.load(fsi);
		String link=prop.getProperty("URL");
		driver.get(link);
		Thread.sleep(2000);
		HomePage homepage=new HomePage(driver);
		return homepage;
	}
	
	
}
