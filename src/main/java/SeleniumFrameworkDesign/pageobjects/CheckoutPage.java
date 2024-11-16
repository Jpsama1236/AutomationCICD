package SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;
import SeleniumFrameworkDesign.pageobjects.ConfirmationPage;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}

Actions a = new Actions(driver);
@FindBy(css=".totalRow button")
WebElement ele1;
@FindBy(css="[placeholder='Select Country']")
WebElement dropdown;
By resultby=By.cssSelector(".ta-results");

@FindBy(xpath="//button[contains(@class,'ta-item')])[2]")
WebElement option;
@FindBy(css=".action__submit")
WebElement submit;




public  void getcheckout(String country) {
	a.sendKeys(dropdown, country).build().perform();
	WaitforElementtoAppear(resultby);
	option.click();
}
public ConfirmationPage submitorder() {
	submit.click();
	return new ConfirmationPage(driver);
}

}
