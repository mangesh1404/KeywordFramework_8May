package Operations;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class UIOperations {
	WebDriver driver;
	
	public UIOperations(WebDriver driver){
		this.driver = driver;
	}
	
	public void perform(Properties p,String operation,String objectName,String objectType,String value)throws Exception {
		switch (operation.toUpperCase()) {
		
	case "CLICK":
		//Perform click
		driver.findElement(this.getObject(p,objectName,objectType)).click();
		break;
		
	case "ENTERTEXT":
		//Set text on control
		driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(value);
		break;
		
	case "GOTOURL":
		//Get url of application
		driver.get(p.getProperty(value));
		break;
		
	case "READTABLE":
		List<WebElement>rows =driver.findElements(this.getObject(p, objectName, objectType));
		for(WebElement row :rows) {
			System.out.println(row.getText());
		}
	}
	}
	
	private By getObject(Properties p,String objectName,String objectType) throws Exception{
		//Find by xpath
		if(objectType.equalsIgnoreCase("XPATH")){
			
			return By.xpath(p.getProperty(objectName));
		}
		//find by class
		else if(objectType.equalsIgnoreCase("ID")){
			
			return By.id(p.getProperty(objectName));
			
		}
	else
	{
		throw new Exception("Wrong object type");
	}
}
}
