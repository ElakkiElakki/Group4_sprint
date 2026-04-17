package utility;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {

	public static String getCellData(int row, int col) {

		try {
			FileInputStream file = new FileInputStream("\"C:\\Users\\2022p\\OneDrive\\Desktop\\flightdata.xlsx\"");

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			String data = sheet.getRow(row).getCell(col).toString();

			workbook.close();
			return data;

		} catch (Exception e) {
			e.printStackTrace(); 
			return "";
		}
	}
}