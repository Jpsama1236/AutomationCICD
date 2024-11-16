package SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class cartpage extends AbstractComponent{
	WebDriver driver;
	// this is to test out the product
@FindBy(css=".cartSection h3")
List<WebElement> productTitles;
@FindBy(css=".totalRow button")
WebElement checkoutbutton;

	public cartpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean getcartproducts(String productname) {
		Boolean match=productTitles.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
		return match;
	}
public CheckoutPage gotocheckoutpage() {
	checkoutbutton.click();
	return new CheckoutPage(driver);

}
}
