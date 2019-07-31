package com.hcl.product.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
			
			List<Product> products = new ArrayList<Product>();			
			while (rowIterator.hasNext()) {
				Product product = new Product();
				Row row = rowIterator.next();
				
				product.setProductId(Long.valueOf(row.getCell(0).getStringCellValue()));
				product.setProductName(row.getCell(1).getStringCellValue());
				product.setQuantity(Integer.parseInt(row.getCell(2).getStringCellValue()));
				product.setManufacturedBy(row.getCell(3).getStringCellValue());
						
				products.add(product);
			}
			file.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

		return null;
	}

}
