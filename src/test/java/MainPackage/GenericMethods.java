package MainPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericMethods {
WebDriver driver;

public GenericMethods(WebDriver driver) {
	this.driver=driver;
}
public WebElement getElement(String locator, String type) {
	type = type.toLowerCase();
	if (type.equals("id")) {
		System.out.println("Element fount with id " + type);
		return this.driver.findElement(By.id(locator));
	}
	else if (type.equals("xpath")) {
		System.out.println("Element fount with xpath " + type);
		return this.driver.findElement(By.xpath(locator));

		
	}
	else if (type.equals("cssSelector")) {
		System.out.println("Element fount with cssSelector " + type);
		return this.driver.findElement(By.cssSelector(locator));

	}
		else if (type.equals("linkText")) {
			System.out.println("Element fount with cssSelector " + type);
			return this.driver.findElement(By.linkText(locator));	
	}
	else {
		System.out.println("Locator is not found");
	return null;
}
	

}
}

