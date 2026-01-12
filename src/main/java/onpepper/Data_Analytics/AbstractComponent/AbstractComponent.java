package onpepper.Data_Analytics.AbstractComponent;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

WebDriver driver;
	
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//button[@aria-label='close']")
	WebElement closeToast;
	
	public void closeToast()  {		
		closeToast.click();
	}
	
	public void waitforElementAppear(WebElement findby) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}

	public void waitForElementsAppear(List<WebElement> elements) {
	  
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	public void waitforclickable(WebElement findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(findby));	
	}
	public void waitForElementToDisappear(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    wait.until(ExpectedConditions.invisibilityOf(element));
	}

	
	public void setChromeData() throws IOException {
		Properties prop = new Properties();
		FileInputStream fsi = new FileInputStream((System.getProperty("user.dir")
				+ "//src//main//java//onpepper//Data_Analytics//resources//globaldata.properties"));
		prop.load(fsi);
		String downloadFilepath = prop.getProperty("downloadFilepath");
		HashMap<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath); 
		chromePrefs.put("download.prompt_for_download", false);
		chromePrefs.put("safebrowsing.enabled", true);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		 driver = new ChromeDriver(options);
	}

}
