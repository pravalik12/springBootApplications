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
	public List<Product> addProducts(@RequestBody List<Product> products) {
		return productService.saveProducts(products);
	}

	@GetMapping("/getAllProducts")
	public List<Product> findAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/getProductById/{id}")
	public Product findProductById(@PathVariable int id) {
		return productService.getProductById(id);
	}

	@GetMapping("/getProductByName/{name}")
	public Product findProductByName(@PathVariable String name) {
		return productService.getProductByName(name);
	}

	@PutMapping("/updateProduct")
	public Product updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return productService.deleteById(id);
	}

}
