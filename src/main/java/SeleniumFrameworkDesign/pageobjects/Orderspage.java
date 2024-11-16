package SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class Orderspage extends AbstractComponent {
	WebDriver driver;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ordernames;
	public Orderspage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

public Boolean verifyorderdisplay(String productname) {
		Boolean match=ordernames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
		return match;
	}
}
