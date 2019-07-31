package com.hcl.product.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.entity.Product;

public class ReadDataServiceImpl implements ReadDataService {

	@Override
	public ResponseData readData(String filePath) {

		try {
			FileInputStream file = new FileInputStream(new File("howtodoinjava_demo.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			
			while (rowIterator.hasNext()) {
				Product product = new Product();
				Row row = rowIterator.next();
				
				product.setProductId(Long.valueOf(row.getCell(0).getStringCellValue()));
				product.setProductName(row.getCell(1).getStringCellValue());
				product.setQuantity(Integer.parseInt(row.getCell(2).getStringCellValue()));
				product.setManufacturedBy(row.getCell(3).getStringCellValue());
				
				
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
			
					Cell cell = cellIterator.next();
					// Check the cell type and format accordingly
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "t");
						break;
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue() + "t");
						break;
					}
				}
				System.out.println("");
			}
			file.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

		return null;
	}

}
