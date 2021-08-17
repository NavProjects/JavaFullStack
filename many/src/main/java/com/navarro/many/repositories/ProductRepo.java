package com.navarro.many.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.navarro.many.models.Category;
import com.navarro.many.models.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {
	// will bring back a list of all Packages
	List<Product> findAll();

	// will bring back a list that doesnt contain(this)
	List<Product> findByCategoriesNotContains(Category category);
	
	boolean existsByName(String name);
}
