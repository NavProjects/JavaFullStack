package com.navarro.many.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.navarro.many.models.Category;
import com.navarro.many.models.Product;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {
	// will bring back a list of all Categories
	List<Category> findAll();
	// will bring back a list that doesnt contain(this)
	List<Category> findByProductsNotContains(Product product);
	
	// Retrieves a list of all categories for a particular product
    List<Category> findAllByProducts(Product product);
    

}
