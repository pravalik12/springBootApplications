package com.mysqlcrud.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysqlcrud.example.entity.Product;
import com.mysqlcrud.example.service.ProductService;
import com.mysqlcrud.example.utils.ResultSet;

@RestController
public class ProductController {

	int successCount = 0;
	int errorCount = 0;
	int exceptionCount = 0;

	@Autowired
	ProductService productService;

	@PostMapping("/addProduct")
	public ResultSet<Product> addProduct(@RequestBody Product product) {

		ResultSet<Product> responseObject = new ResultSet<Product>();
		try {
			if (!ObjectUtils.isEmpty(product)) {
				if (!(product.getName() == null) && !(product.getPrice() == 0)) {
					responseObject = productService.saveProduct(product);
				} else {
					errorCount++;
					responseObject.setErrorCount(errorCount);
					responseObject.setErrorMessage("Please Provide Name &/ Price correctly");
				}
			} else {
				errorCount++;
				responseObject.setErrorCount(errorCount);
				responseObject.setErrorMessage("Object is null, Pls provide a valid Object");
			}
		} catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionCount(exceptionCount);
			responseObject.setExceptionMessage(e.getMessage());
		}
		return responseObject;
	}

	@PostMapping("/addProducts")
	public ResultSet<List<Product>> addProducts(@RequestBody List<Product> products) {
		
		ResultSet<List<Product>> responseObject = new ResultSet<>();
		try {
			responseObject = productService.saveProducts(products);
			
		}catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionCount(exceptionCount);
			responseObject.setExceptionMessage(e.getMessage());		
		}
		return responseObject;
	}

	@GetMapping("/getAllProducts")
	public ResultSet<List<Product>>findAllProducts() {
		
		ResultSet<List<Product>> responseObject = new ResultSet<>();
		try {
			responseObject = productService.getAllProducts();
			
		}catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionCount(exceptionCount);
			responseObject.setExceptionMessage(e.getMessage());		
		}
		return responseObject;
	}

	@GetMapping("/getProductById/{id}")
	public ResultSet<Product> findProductById(@PathVariable int id) {
		
		ResultSet<Product> responseObject = new ResultSet<Product>();
		try {
			responseObject = productService.getProductById(id);
			
		}catch(Exception e) {
			exceptionCount++;
			responseObject.setExceptionCount(exceptionCount);
			responseObject.setExceptionMessage(e.getMessage());		
		}
		return responseObject;
	}

	@GetMapping("/getProductByName/{name}")
	public ResultSet<Product> findProductByName(@PathVariable String name) {
		
		ResultSet<Product> responseObject = new ResultSet<Product>();
		try {
			responseObject = productService.getProductByName(name);
		}catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionCount(exceptionCount);
			responseObject.setExceptionMessage(e.getMessage());		
		}
		return responseObject;
	}

	@PutMapping("/updateProduct")
	public ResultSet<Product> updateProduct(@RequestBody Product product) {
		
		ResultSet<Product> responseObject = new ResultSet<Product>();
		try {
			responseObject = productService.updateProduct(product);
			
		}catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionCount(exceptionCount);
			responseObject.setExceptionMessage(e.getMessage());		
		}
		return responseObject;
	}

	@DeleteMapping("/delete/{id}")
	public ResultSet<Product> deleteProduct(@PathVariable int id) {
		
		ResultSet<Product> responseObject = new ResultSet<Product>();
		try {
			responseObject = productService.deleteById(id);
			
		}catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionCount(exceptionCount);
			responseObject.setExceptionMessage(e.getMessage());		
		}
		return responseObject;
	}
	
	@GetMapping("/getMessage")
	public String getMessageFromService() {
		System.out.println("Hello World.");
		return "Connection has been established..!";
		
	}

}
