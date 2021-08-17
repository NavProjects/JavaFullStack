package com.navarro.many.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="products")
public class Product {
	//Member Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Can not be blank")
    private String name;
    @NotBlank(message = "Can not be blank")
    private String description;
    @NotNull(message = "Must not be blank")
    @Min(value=0, message="Must not be negative")
    private float price;
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(updatable=false)
    private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")  
    private Date updatedAt;
    // mid table association
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "categories_products", 
        joinColumns = @JoinColumn(name = "product_id"), 
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
    // Constructor
    public Product() {
        
    }

    
//    // retrieve an instance of a category using another method in the service.
//    Category thisCategory = findCategoryById(categoryId);
//    
//    // retrieve an instance of a product using another method in the service.
//    Product thisProduct = findProductById(productId);
//    // add the product to this category's list of products
//    thisCategory.getProducts().add(thisProduct);
//    
//    // Save thisCategory, since you made changes to its product list.
//    categoryRepository.save(thisCategory);	
//copy
//You can also add the category to that product's category list, and it does the same thing.
//
//    // This has the same affect as above:
//    thisProduct.getCategories().add(thisCategory);	// add the category to this products's list of categories
//    productRepository.save(thisProduct);	// Save thisProduct, since you made changes to its category list.

    // Getters and Setters
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAT() {
		return updatedAt;
	}

	public void setUpdatedAT(Date updatedAT) {
		this.updatedAt = updatedAT;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
