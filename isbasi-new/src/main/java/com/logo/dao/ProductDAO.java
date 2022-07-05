package com.logo.dao;

import java.util.List;

import com.logo.model.Product;

public interface ProductDAO {// ProductDAO, tüm DAO layer'larının kullanacağı CRUD fonksiyonlarını içerir.
	
	public Product findProduct(int id);
	
	public List<Product> getAllProducts();
	
	public void deleteProduct(int id);
	
	public void saveProduct(Product p);
	
	
	
	

}
