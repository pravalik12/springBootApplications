package com.mysqlcrud.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysqlcrud.example.entity.Product;
import com.mysqlcrud.example.repository.ProductRepository;
import com.mysqlcrud.example.utils.ResultSet;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;

	int exceptionCount = 0;
	int successCount = 0;

	public ResultSet<Product> saveProduct(Product product) {

		ResultSet<Product> responseObject = new ResultSet<>();
		try {
			responseObject.setData(repository.save(product));
			successCount++;
			responseObject.setSuccessCount(successCount);
		} catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionMessage(e.getMessage());
			responseObject.setExceptionCount(exceptionCount);
		}
		return responseObject;
	}

	public ResultSet<List<Product>> saveProducts(List<Product> products) {

		ResultSet<List<Product>> responseObject = new ResultSet<>();
		try {
			responseObject.setData(repository.saveAll(products));
			successCount++;
			responseObject.setSuccessCount(successCount);
		} catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionMessage(e.getMessage());
			responseObject.setExceptionCount(exceptionCount);
		}
		return responseObject;
	}

	public ResultSet<List<Product>> getAllProducts() {

		ResultSet<List<Product>> responseObject = new ResultSet<>();
		try {
			responseObject.setData(repository.findAll());
			successCount++;
			responseObject.setSuccessCount(successCount);
		} catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionMessage(e.getMessage());
			responseObject.setExceptionCount(exceptionCount);
		}
		return responseObject;
	}

	public ResultSet<Product> getProductById(int id) {

		ResultSet<Product> responseObject = new ResultSet<>();

		try {
			responseObject.setData(repository.findById(id).orElse(null));
			successCount++;
			responseObject.setSuccessCount(successCount);
		} catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionMessage(e.getMessage());
			responseObject.setExceptionCount(exceptionCount);
		}

		return responseObject;
	}

	public ResultSet<Product> getProductByName(String name) {

		ResultSet<Product> responseObject = new ResultSet<>();
		try {
			responseObject.setData(repository.findByName(name));
			successCount++;
			responseObject.setSuccessCount(successCount);
		} catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionMessage(e.getMessage());
			responseObject.setExceptionCount(exceptionCount);
		}
		return responseObject;
	}

	public ResultSet<Product> deleteById(int id) {

		ResultSet<Product> responseObject = new ResultSet<>();
		try {
			repository.deleteById(id);
			responseObject.setData("Product deleted with Id --> " + id);
			successCount++;
			responseObject.setSuccessCount(successCount);
		} catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionMessage(e.getMessage());
			responseObject.setExceptionCount(exceptionCount);
		}
		return responseObject;
	}

	public ResultSet<Product> updateProduct(Product product) {

		ResultSet<Product> responseObject = new ResultSet<>();
		try {
			Product existingProduct = repository.findById(product.getId()).orElse(null);
			existingProduct.setName(product.getName());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setQuantity(product.getQuantity());
			responseObject.setData(repository.save(existingProduct));
			successCount++;
			responseObject.setSuccessCount(successCount);

		} catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionMessage(e.getMessage());
			responseObject.setExceptionCount(exceptionCount);
		}
		return responseObject;
	}
}
