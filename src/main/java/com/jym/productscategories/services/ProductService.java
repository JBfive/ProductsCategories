package com.jym.productscategories.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jym.productscategories.models.Product;
import com.jym.productscategories.repositories.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository proRepo;
	
	public ProductService(ProductRepository proRepo) {
		this.proRepo = proRepo;
	}
	public Product createProduct(Product product) {
		return proRepo.save(product);
	}
	public List<Product> allProducts(){
		return proRepo.findAll();
	}
	public Product findProduct(Long id) {
		Optional<Product> optionalProduct = proRepo.findById(id);
		if(optionalProduct.isPresent()) {
			return optionalProduct.get();
		} else {
			return null;
		}
	}
	//public Product updateProduct (Long id)
	//update with List<Category>
	public void update(Product product) {
		product.setUpdatedAt(new Date());
		proRepo.save(product);
	}
}
