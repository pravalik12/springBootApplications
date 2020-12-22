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

	public List<Product> saveProducts(List<Product> products) {
		return repository.saveAll(products);
	}

	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	public Product getProductById(int id) {
		return repository.findById(id).orElse(null);
	}

	public Product getProductByName(String name) {
		return repository.findByName(name);
	}

	public String deleteById(int id) {
		repository.deleteById(id);
		return "Product deleted with id --" + id;
	}

	public Product updateProduct(Product product) {
		Product existingProduct = repository.findById(product.getId()).orElse(null);
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setQuantity(product.getQuantity());
		return repository.save(existingProduct);
	}
}
