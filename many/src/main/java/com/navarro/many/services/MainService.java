package com.navarro.many.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navarro.many.models.Category;
import com.navarro.many.models.Product;
import com.navarro.many.repositories.CategoryRepo;
import com.navarro.many.repositories.ProductRepo;

@Service
public class MainService {
	
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	// for CATEGORY
		// GET
		// GET all Category
		public List<Category> allCategories(){
			return categoryRepo.findAll();
		}
		// GET not containing
		public List<Category> showCatExcept(Product product){
			return categoryRepo.findByProductsNotContains(product);			
		}
		// GET one category
		//GET one product
		public Category getCat(Long id) {
			Optional<Category> optionalCategory = categoryRepo.findById(id);
			if (optionalCategory.isPresent()) {
				return optionalCategory.get();
			}
			return null;
		}
		// POST create category
		public Category createCat(Category d) {
			return categoryRepo.save(d);
		}
		// PUT update category
		public Category updateCat(Category d) {
			return categoryRepo.save(d);
		}
	// for PRODUCT	
		// GET
		// GET not containing
		public List<Product> showProdExcept(Category category){
			return productRepo.findByCategoriesNotContains(category);			
		}
		//GET all products
		public List<Product> allProducts(){
			return productRepo.findAll();
		}
		//GET one product
		public Product getProd(Long id) {
			Optional<Product> optionalProduct = productRepo.findById(id);
			if (optionalProduct.isPresent()) {
				return optionalProduct.get();
			}
			return null;
		}

		// POST create product
		public Product createProduct(Product n) {
			return productRepo.save(n);
		}
		// PUT update product
		public Product updateProduct(Product n) {
			return productRepo.save(n);
		}
}

