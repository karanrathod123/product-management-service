package com.hcl.product.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
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
import com.hcl.product.repository.ProductRepository;

@Service
public class ReadDataServiceImpl implements ReadDataService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ResponseData readData(String filePath) {

		List<Product> products = saveDatetoDatabase(readDataFromFile(filePath));
		ResponseData responseData = new ResponseData();
		responseData.setData(products);
		responseData.setMessage("File Reading completed");
		responseData.setHttpStatus(HttpStatus.OK);

		return responseData;
	}

	private List<Product> readDataFromFile(String filePath) {

		List<Product> products = new ArrayList<Product>();
		try {

			FileInputStream file = new FileInputStream(getClass().getClassLoader().getResource(filePath).getFile());
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Product product = new Product();
				Row row = rowIterator.next();

				product.setProductId(new Double(row.getCell(0).getNumericCellValue()).longValue());
				product.setProductName(row.getCell(1).getStringCellValue());
				product.setQuantity((int) row.getCell(2).getNumericCellValue());
				product.setManufacturedBy(row.getCell(3).getStringCellValue());

				products.add(product);
			}
			file.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
		return products;
	}

	public List<Product> saveDatetoDatabase(List<Product> productList) {

		List<Product> updatedProductList = new ArrayList<Product>();
		for (Product p : productList) {
			Product product = productRepository.findLatestProduct(p.getProductId());
			if (product != null) {
				p.setProductVersion(0.1);
				if (!product.getManufacturedBy().equalsIgnoreCase(p.getManufacturedBy())
						|| product.getQuantity() != p.getQuantity()) {
					p.setProductVersion(
							Double.parseDouble(new DecimalFormat("#.#").format(product.getProductVersion() + p.getProductVersion())));
					productRepository.save(p);
				}
			} else {
				p.setProductVersion(0.1);
				productRepository.save(p);
			}

			updatedProductList.add(p);
		}

		return updatedProductList;
	}

}
