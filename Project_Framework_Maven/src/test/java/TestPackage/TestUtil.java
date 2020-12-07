package TestPackage;

import java.util.ArrayList;

import co.in.bymat.seleniumTraining.ExcelReader;

public class TestUtil {

	static ExcelReader reader;
	
	public static ArrayList<Object[]> getDataFromExcel() {
		ArrayList<Object[]> myData= new ArrayList<Object[]>();
		
		try {
			reader=new ExcelReader("G:\\Person\\Framework2020\\Workbook1.xlsx");
		}
		catch(Exception e){
		e.printStackTrace();	
		}
		for (int rowNum=2; rowNum<=reader.getRowCount("Sheet2"); rowNum++) {
			
			String firstname=reader.getCellData("Sheet2", "firstname", rowNum);
			String lastname=reader.getCellData("Sheet2", "lastname", rowNum);
			String Address=reader.getCellData("Sheet2", "Address", rowNum);
			String Emailaddress=reader.getCellData("Sheet2", "Emailaddress", rowNum);
			String Phone=reader.getCellData("Sheet2", "Phone", rowNum);
			
			Object ob[]= {firstname,lastname,Address,Emailaddress,Phone};
			myData.add(ob);
		}
		return myData;
		}
		
}
