package SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class productcatalogue extends AbstractComponent {
WebDriver driver;
		// TODO Auto-generated method stub
public productcatalogue(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}

@FindBy(css=".mb-3")
List<WebElement> products;
@FindBy(css=".ng-animating")
WebElement ele;

By cart=By.cssSelector(".card-body button:last-of-type");
By products1=By.cssSelector(".mb-3");
By toastmessage =By.cssSelector("#toast-container");

public List<WebElement> getproductlist() {
	WaitforElementtoAppear(products1);
	return products;	
}
public  WebElement getProductName(String productname) {
	WebElement prod=getproductlist().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
	return prod;
}
public void addtocart(String product) throws InterruptedException {
	WebElement prod=getProductName(product);
	prod.findElement(cart).click();
	WaitforElementtoAppear(toastmessage);
	WaitforElementtoDisappear(ele);
}

}

