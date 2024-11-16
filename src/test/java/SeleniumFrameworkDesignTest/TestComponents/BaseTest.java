package SeleniumFrameworkDesignTest.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFrameworkDesign.pageobjects.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Landingpage lp;
	public WebDriver initializeDriver() throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/SeleniumFrameworkDesign/resources/GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		if (browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C://Users//Sama Jayaprakash//Documents//chromedriver-win64//chromedriver-win64//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			//WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")){
			options.addArguments("headless");
			}	
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//full screen
		}
		else if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Sama Jayaprakash\\Downloads\\geckodriver-v0.34.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	@BeforeMethod(alwaysRun=true)
	public Landingpage launchApplication() throws IOException {
		driver=initializeDriver();
		lp=new Landingpage(driver);
		lp.goTo();
		return lp;
	}
	@AfterMethod(alwaysRun=true)
	public void teardown() {
		driver.close();
	}
	
	public String getScreenshot(String TestcaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts= (TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"//reports//"+TestcaseName+".png");
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir")+"//reports//"+TestcaseName+".png";
	}
	
	public List<HashMap<String, String>> getJsonDatatoMap(String filepath) throws IOException {
		String jsoncontent =FileUtils.readFileToString(new File(System.getProperty("user.dir")+filepath),StandardCharsets.UTF_8);
	
		ObjectMapper mapper=new ObjectMapper();
	List<HashMap<String,String>> data=mapper.readValue(jsoncontent , new TypeReference<List<HashMap<String,String>>>(){
		
	});
	return data;
	
	}
}
