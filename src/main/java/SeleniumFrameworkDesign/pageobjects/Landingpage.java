package SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class Landingpage extends AbstractComponent{
WebDriver driver;
		// TODO Auto-generated method stub
public Landingpage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}

@FindBy(id="userEmail")
WebElement useremail;

@FindBy(id="userPassword")
WebElement paswd;

@FindBy(id="login")
WebElement submit;
@FindBy(css="[class*='flyInOut']")
WebElement errormessage;

public void goTo() {
	driver.get("https://rahulshettyacademy.com/client");
}
public productcatalogue logintoapplication(String email,String password) {
	useremail.sendKeys(email);
	paswd.sendKeys(password);
	submit.click();
	 return new productcatalogue(driver);
}
public String getErrormessage() {
	WaitforWebElementtoAppear(errormessage);
	return errormessage.getText();
}

}

