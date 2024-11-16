package SeleniumFrameworkDesignTest;
import SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.pageobjects.CheckoutPage;
import SeleniumFrameworkDesign.pageobjects.Landingpage;
import SeleniumFrameworkDesign.pageobjects.Orderspage;
import SeleniumFrameworkDesign.pageobjects.cartpage;
import SeleniumFrameworkDesign.pageobjects.productcatalogue;
import SeleniumFrameworkDesignTest.TestComponents.BaseTest;


public class StandaloneTest2 extends BaseTest {
	String productName="ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitorder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		// TODO Auto-generated method stub

		productcatalogue pc=lp.logintoapplication(input.get("email"), input.get("password"));
		List<WebElement>list1=pc.getproductlist();
		pc.addtocart(input.get("productname"));
		cartpage cp=pc.gotocart();
		Boolean match=cp.getcartproducts(input.get("productname"));
		Assert.assertTrue(match);
		
		CheckoutPage cp1=cp.gotocheckoutpage();
		cp1.getcheckout("india");
		//driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();
		//driver.findElement(By.xpath("//ul/li/button[contains(.,'Checkout')]")).click();		
		ConfirmationPage confirmationPage = cp1.submitorder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods= {"submitorder"})
	public void OrderHistoryTest() {
		productcatalogue pc=lp.logintoapplication("anshika@gmail.com", "Iamking@000");
		Orderspage op=pc.gotoorders();
		Assert.assertTrue(op.verifyorderdisplay(productName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		/*HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "anshika@gmail.com");
		map.put("paswd", "Iamking@000");
		map.put("productname", "ZARA COAT 3");
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("paswd", "Iamking@000");
		map1.put("productname", "ADIDAS ORIGINAL");
		return new Object[][] {{map},{map1}};*/
		List<HashMap<String,String>> map=getJsonDatatoMap("//src//test//java//JP//TestData//PurchaseOrder.json");
		return new Object[][] {{map.get(0)},{map.get(1)}};
	}
}
