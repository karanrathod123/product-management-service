package com.hcl.product.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.entity.Product;
import com.hcl.product.exception.InvalidInputException;
import com.hcl.product.repository.ProductRepository;

@Service
public class ReadDataServiceImpl implements ReadDataService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ResponseData readData(String filePath) throws InvalidInputException {

		List<Product> products = saveDatetoDatabase(readDataFromFile(filePath));
		ResponseData responseData = new ResponseData();
		responseData.setData(products);
		responseData.setMessage("File Reading completed");
		responseData.setHttpStatus(HttpStatus.OK);

		return responseData;
	}

	private List<Product> readDataFromFile(String filePath) throws InvalidInputException {

		List<Product> products = new ArrayList<>();
		try {

			FileInputStream file = new FileInputStream(getClass().getClassLoader().getResource(filePath).getFile());
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Product product = new Product();
				Row row = rowIterator.next();

				product.setProductId((long)row.getCell(0).getNumericCellValue());
				product.setProductName(row.getCell(1).getStringCellValue());
				product.setQuantity((int) row.getCell(2).getNumericCellValue());
				product.setManufacturedBy(row.getCell(3).getStringCellValue());

				products.add(product);
			}
			file.close();
		} catch (IOException e) {
			throw new InvalidInputException("Invalid File");
		}
		return products;
	}

	@Override
	public List<Product> saveDatetoDatabase(List<Product> productList) {

		List<Product> updatedProductList = new ArrayList<>();
		for (Product p : productList) {
			Product product = productRepository.findLatestProduct(p.getProductId());
			if (product != null) {
				p.setProductVersion(product.getProductVersion());
				if (!product.getManufacturedBy().equalsIgnoreCase(p.getManufacturedBy())
						|| !product.getQuantity().equals(p.getQuantity())) {
					p.setProductVersion(product.getProductVersion() + 1.0);
					productRepository.save(p);
				}
			} else {
				p.setProductVersion(1.0);
				productRepository.save(p);
			}
			updatedProductList.add(p);
		}

		return updatedProductList;
	}

}
