package SeleniumFrameworkDesignTest;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.pageobjects.cartpage;
import SeleniumFrameworkDesign.pageobjects.productcatalogue;
import SeleniumFrameworkDesignTest.TestComponents.BaseTest;
import SeleniumFrameworkDesignTest.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"errorhandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {
		
		// TODO Auto-generated method stub
		lp.logintoapplication("anshika@gmail.com", "Iamkin000");
		Assert.assertEquals("Incorrect email or password.", lp.getErrormessage());
		
	}
	@Test
	public void ProductValidation() throws IOException, InterruptedException {
		
		// TODO Auto-generated method stub
		String productname="ZARA COAT 3";
		productcatalogue pc=lp.logintoapplication("rahulshetty@gmail.com", "Iamking@000");
		List<WebElement>list1=pc.getproductlist();
		pc.addtocart(productname);
		cartpage cp=pc.gotocart();
		Boolean match=cp.getcartproducts("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}
}
