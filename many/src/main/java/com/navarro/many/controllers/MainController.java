package com.navarro.many.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.navarro.many.models.Category;
import com.navarro.many.models.Product;
import com.navarro.many.services.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService service;
	
	//render dash board
	@GetMapping("/")
	public String index() {
		return "index.jsp";
	}
	// PRODUCTS ROUTES
	// render Create Product page
	@GetMapping("/products/new")
	public String createProdPage(@ModelAttribute("product") Product product) {
		return "/product/newProduct.jsp";
	}
	// POST 
	@PostMapping("products/new")
	public String createProd(@Valid
					@ModelAttribute Product product,
					BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "product/newProduct.jsp";
		} else {
			service.createProduct(product);
			return "redirect:/";
		}
	}
	//GET one product
	@GetMapping("products/{id}")
	public String showProduct(@PathVariable("id") Long id, Model model) {
		Product thisProduct = service.getProd(id);
		if(thisProduct == null) {			
			return "redirect:/";
		}
		else {			
			model.addAttribute("product", thisProduct);

			List<Category> allCats = service.showCatExcept(thisProduct);
			model.addAttribute("categories", allCats);
			return "/product/showProduct.jsp";
		}
	}
	@PostMapping("products/{id}")
	public String addCatToProd(@PathVariable("id") Long id,
				@RequestParam("catId") Long catId) {
		Category c = service.getCat(catId);
		Product p = service.getProd(id);
		// add category to product
		p.getCategories().add(c);
		service.updateProduct(p);
		return "redirect:/products/" + id;
	}
	//CATEGORIES ROUTES
	// render Create category
	@GetMapping("/categories/new")
	public String createCatPage(@ModelAttribute("category") Category category) {
		return "/category/newCategory.jsp";
	}
	@PostMapping("categories/new")
	public String createCat(@Valid
					@ModelAttribute Category category,
					BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "category/newCategory.jsp";
		} else {
			service.createCat(category);
			return "redirect:/";
		}
	}
	// render GET one category
	//GET one product
	@GetMapping("categories/{id}")
	public String showCategory(@PathVariable("id") Long id, Model model) {
		Category thisCategory = service.getCat(id);
		if(thisCategory == null) {			
			return "redirect:/";
		}
		else {			
			model.addAttribute("category", thisCategory);

			List<Product> allProds = service.showProdExcept(thisCategory);
			model.addAttribute("products", allProds);
			return "/category/showCategory.jsp";
		}
	}
	@PostMapping("categories/{id}")
	public String addProdToCat(@PathVariable("id") Long id,
				@RequestParam("prodId") Long prodId) {
		Product p = service.getProd(prodId);
		Category c = service.getCat(id);
		// add category to product
		c.getProducts().add(p);
		service.updateCat(c);
		return "redirect:/categories/" + id;
	}
}