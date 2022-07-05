package com.logo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logo.model.Product;
import com.logo.request.RequestIntClass;
import com.logo.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {// Contoller ile products'ı 3 ana başlık altına bölündü. Hibernate, JdbcTemplate ve Jdbc, kendi mapping'leriyle aynı işlemleri yapmak adına düzenlendi.
	
	// allproducts endpoint'i tüm product'ları çekecektir.
	// saveproduct verilen product'u tablo'ya kaydeder.
	// deleteproduct verilen id'ye göre product'ı tablodan siler.
	// deleteproduct verilen id'ye göre product'ı tabloda bulur.
	
	@Autowired
	private ProductService productService; // productService sayesinde hibernate, JdbcTemplate ve Jdbc fonksiyonlarını kullanabiliriz.
	
	// RequestIntClass, id değerini JSON halinde obje olarak çekmek için kullanıldı. Bu olayın ana sebebi Postman'a uymak adına yapılmıştır.
	
	@GetMapping(value = "/hibernate/allproducts")
	public List<Product> getProductsH() {
		return productService.getAllProductsHib();
	}
	
	@PostMapping(value = "/hibernate/saveproduct")
	public void saveProductH(@RequestBody Product productRequest) {
		productService.saveProductHib(productRequest);
		System.out.println(productRequest);
	}
	
	@PostMapping(value = "/hibernate/deleteproduct")
	public void deleteProductH(@RequestBody RequestIntClass id) {
		int i=Integer.parseInt(id.getId().toString());
		productService.deleteProductHib(i);
	}
	
	@GetMapping(value = "/hibernate/findproduct")
	public Product findProductH(@RequestBody RequestIntClass id) {
		int i=Integer.parseInt(id.getId().toString());
		return productService.findProductHib(i);
	}
	
	
	@GetMapping(value = "/jdbctemplate/allproducts")
	public List<Product> getProductsJ() {
		return productService.getAllProductsJdbcT();
	}
	
	@PostMapping(value = "/jdbctemplate/saveproduct")
	public void saveProductJ(@RequestBody Product productRequest) {
		productService.saveProductJdbcT(productRequest);
		System.out.println(productRequest);
	}
	
	@PostMapping(value = "/jdbctemplate/deleteproduct")
	public void deleteProductJ(@RequestBody RequestIntClass id) {
		int i=Integer.parseInt(id.getId().toString());
		productService.deleteProductJdbcT(i);
	}
	
	@GetMapping(value = "/jdbctemplate/findproduct")
	public Product findProductJ(@RequestBody RequestIntClass id) {
		int i=Integer.parseInt(id.getId().toString());
		return productService.findProductJdbcT(i);
	}
	
	
	@GetMapping(value = "/jdbc/allproducts")
	public List<Product> getProductsJb() {
		return productService.getAllProductsJdbc();
	}
	
	@PostMapping(value = "/jdbc/saveproduct")
	public void saveProductJb(@RequestBody Product productRequest) {
		productService.saveProductJdbc(productRequest);
		System.out.println(productRequest);
	}
	
	@PostMapping(value = "/jdbc/deleteproduct")
	public void deleteProductJb(@RequestBody RequestIntClass id) {
		int i=Integer.parseInt(id.getId().toString());
		productService.deleteProductJdbc(i);
	}
	
	@GetMapping(value = "/jdbc/findproduct")
	public Product findProductJb(@RequestBody RequestIntClass id) {
		int i=Integer.parseInt(id.getId().toString());
		return productService.findProductJdbc(i);
	}

}
