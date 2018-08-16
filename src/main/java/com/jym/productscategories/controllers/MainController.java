package com.jym.productscategories.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jym.productscategories.models.Category;
import com.jym.productscategories.models.Product;
import com.jym.productscategories.services.CategoryService;
import com.jym.productscategories.services.ProductService;

@Controller
public class MainController {
	private final ProductService proServ;
	private final CategoryService catServ;
	
	public MainController(ProductService proServ, CategoryService catServ) {
		this.catServ = catServ;
		this.proServ = proServ;
	}
	@RequestMapping("/products/new")
	public String prod(@Valid @ModelAttribute("products") Product product) {
		return "newproduct.jsp";
	}
	@PostMapping("/create_product")
	public String newProduct(@Valid @ModelAttribute("products") Product product, BindingResult result) {
		if(result.hasErrors()) {
			return "newproduct.jsp";
		} else {
			proServ.createProduct(product);
			return "redirect:/products/new";
		}
	}
	@RequestMapping("/categories/new")
	public String cate(@Valid @ModelAttribute("categories") Category category) {
		return "newcategory.jsp";
	}
	@PostMapping("/create_category")
	public String newCategory(@Valid @ModelAttribute("categories") Category category, BindingResult result) {
		if(result.hasErrors()) {
			return "newcategory.jsp";
		} else {
			catServ.createCategory(category);
			return "redirect:/categories/new";
		}
	}
	@RequestMapping("/products/{id}")
	public String disPro(@PathVariable("id") Long id, Category category, Product product, Model model) {
		List<Category> categoryList = catServ.allCategories();
		List<Category> proCat = proServ.findProduct(id).getCategories();	
		for(int i=0; i < proCat.size(); i++) {
			if(categoryList.contains(proCat.get(i))) {
				categoryList.remove(proCat.get(i));
			}
		}
		model.addAttribute("category", categoryList);
		model.addAttribute("proCat", proCat);
		model.addAttribute("product", proServ.findProduct(id));
		return "products.jsp";
	}
	@RequestMapping("/categories/{id}")
	public String disCat(@PathVariable("id") Long id, Product product, Category category, Model model) {
		List<Product> productList = proServ.allProducts();
		List<Product> catPro = catServ.findCategory(id).getProducts();	
		for(int i=0; i < catPro.size(); i++) {
			if(productList.contains(catPro.get(i))) {
				productList.remove(catPro.get(i));
			}
		}
		model.addAttribute("product", productList);
		model.addAttribute("catPro", catPro);
		model.addAttribute("product", productList);
		model.addAttribute("category", catServ.findCategory(id));
		return "categories.jsp";
	}
	@PostMapping("/add_category")
	public String addCat(@RequestParam("categories") Long cat_id, @RequestParam("product") Long prod_id, Model model) {
		
		Category newCat = catServ.findCategory(cat_id);
		Product updatedProduct = proServ.findProduct(prod_id);
		List<Category> categoryList  = updatedProduct.getCategories();
		categoryList.add(newCat);
		updatedProduct.setCategories(categoryList);
		proServ.update(updatedProduct);
		return "redirect:/products/" + prod_id;
	}
	@PostMapping("/add_product")
	public String addPro(@RequestParam("products") Long pro_id, @RequestParam("category") Long cat_id, Model model) {
		
		Product newPro = proServ.findProduct(pro_id);
		Category updatedCategory = catServ.findCategory(cat_id);
		List<Product> productList  = updatedCategory.getProducts();
		productList.add(newPro);
		updatedCategory.setProducts(productList);
		catServ.update(updatedCategory);
		return "redirect:/categories/" + cat_id;
	}
}
