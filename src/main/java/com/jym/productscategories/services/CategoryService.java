package com.jym.productscategories.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jym.productscategories.models.Category;
import com.jym.productscategories.models.Product;
import com.jym.productscategories.repositories.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository catRepo;
	
	public CategoryService(CategoryRepository catRepo) {
		this.catRepo = catRepo;
	}
	public Category createCategory(Category category) {
		return catRepo.save(category);
	}
	public List<Category> allCategories(){
		return catRepo.findAll();
	}
	public Category findCategory(Long id) {
		Optional<Category> optionalCategory = catRepo.findById(id);
		if(optionalCategory.isPresent()) {
			return optionalCategory.get();
		} else {
			return null;
		}
	}
//	public Category updateCategory(Long id)
	//update with List<Product>
	public void update(Category category) {
		category.setUpdatedAt(new Date());
		catRepo.save(category);
	}
}
