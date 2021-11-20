package com.execution;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.excelRead.ReadExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import Operations.ReadProperty;
import Operations.UIOperations;

public class TestExecution {
	DataFormatter df = new DataFormatter();
	
	@Test
	public void test01()throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		ReadExcel readfile=new ReadExcel();
		ReadProperty prop=new ReadProperty();
		
		 Properties property =  prop.getObjectRepository();
		  
		 UIOperations operation= new UIOperations(driver);
		 
		 
		 Sheet sheet =readfile.getSheet("TestCase.xlsx", "Login");
		 
		 int rows=sheet.getPhysicalNumberOfRows();//no. of rows in whci we have a data
		 System.out.println(rows);
		 for(int i=1;i<rows;i++) {
			 
			Row row= sheet.getRow(i);
			
			String cellValue=df.formatCellValue(row.getCell(0));
			
			if(cellValue.length()==0){
	    		
	    			System.out.println(row.getCell(1).toString()+"----"+ row.getCell(2).toString()+"----"+
	    			row.getCell(3).toString()+"----"+ row.getCell(4).toString());
	    			Cell cell=row.getCell(4);
	    			String data= df.formatCellValue(cell);	    				
	    			operation.perform(property, row.getCell(1).toString(), row.getCell(2).toString(),
	    				row.getCell(3).toString(), data);
	    	    }
	    		else{
	    		
	    				System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
	    			}
			 
		 }
		 
		// Assert.assertEquals(driver.getTitle(), "JavaByKiran | User");
		 
	}
	
	
	
	
}
