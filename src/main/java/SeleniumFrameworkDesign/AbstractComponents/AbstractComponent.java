package SeleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesign.pageobjects.Orderspage;
import SeleniumFrameworkDesign.pageobjects.cartpage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(css="[routerlink*='cart']")
WebElement cart;
@FindBy(css="[rouerlink*='myorders]")
WebElement orders;

public void WaitforElementtoAppear(By findby) {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
			}
public void WaitforWebElementtoAppear(WebElement findby) {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findby));
	}
public void WaitforElementtoDisappear(WebElement ele) throws InterruptedException {
	Thread.sleep(1000);
}

public cartpage gotocart()
{
	cart.click();
	cartpage cp=new cartpage(driver);
	return cp;
}
public Orderspage gotoorders()
{
	orders.click();
	Orderspage op=new Orderspage(driver);
	return op;
}
}
