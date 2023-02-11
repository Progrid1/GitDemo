import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public static ArrayList<String> getData(String testCaseName) throws IOException {
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("C:\\Users\\mertk\\OneDrive\\Belgeler\\demodata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				Iterator<Row> row = sheet.iterator();
				Row firstRow = row.next();

				Iterator<Cell> cell = firstRow.cellIterator();

				int count = 0;
				int desiredColumnNumber = 0;

				while (cell.hasNext()) {
					Cell value = cell.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						desiredColumnNumber = count;

					}
					count++;
				}

				while (row.hasNext()) {
					Row r = row.next();
					if (r.getCell(desiredColumnNumber).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						Iterator<Cell> ci = r.cellIterator();
						while (ci.hasNext()) {
							Cell c=ci.next();
							if (c.getCellTypeEnum()==CellType.STRING) {
								a.add(c.getStringCellValue());
							}else {
								
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
							
							
							
							
						}

					}

				}

			}
		}
		return a;

	}

	public static void main(String[] args) throws IOException {
	System.out.println(	getData("Login").toString());
	}

}
