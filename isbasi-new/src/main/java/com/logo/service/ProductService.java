package com.logo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.logo.model.Product;
import com.logo.dao.HibernateProductDAO;
import com.logo.dao.JDBCProductDAO;
import com.logo.dao.JDBCTemplateProductDAO;
import com.logo.dao.ProductDAO;

@Service
@Scope("prototype")
public class ProductService {
	
	
    // ProductDAO sayesinde 3 farklı DAO layer'ına erişim sağlayabiliyoruz. Bu sayede dinamik polimorfizm sağlamış oluyoruz.
	private ProductDAO hibProductDao;
	private ProductDAO jdbcTemplateProductDao;
	private ProductDAO jdbcProductDao;
	
	@Autowired
	public ProductService(HibernateProductDAO hProductDao,JDBCTemplateProductDAO jtProductDao, JDBCProductDAO jbProductDao) {// Değerlerin ilk initialize edilmesi.
		hibProductDao = hProductDao;
		jdbcTemplateProductDao = jtProductDao;
		jdbcProductDao=jbProductDao;
		
	}
	
	// Aşağıda tüm DAO layer'larının aynı işlemlerini fonksiyonlara bölünmüş hali yer alıyordur.
	
    // Hibernate işlemleri. Transactional annotation ile Hibernate db ile görüşmeye başlayabilir.
	
	
	@Transactional
	public List<Product> getAllProductsHib() {
		return hibProductDao.getAllProducts();
	}
	
	@Transactional
	public Product findProductHib(int id) {
		return hibProductDao.findProduct(id);
	}
	
	@Transactional
	public void deleteProductHib(int id) {
		hibProductDao.deleteProduct(id);
	}
	
	@Transactional
	public void saveProductHib(Product p) {
		hibProductDao.saveProduct(p);
	}
	
	
	// JdbcTemplate işlemleri.
	
	public List<Product> getAllProductsJdbcT(){
		return jdbcTemplateProductDao.getAllProducts();
	}
	
	public Product findProductJdbcT(int id) {
		return jdbcTemplateProductDao.findProduct(id);
	}

	
	public void saveProductJdbc(Product p) {
		jdbcProductDao.saveProduct(p);
	}
	
	public void deleteProductJdbc(int id) {
		jdbcProductDao.deleteProduct(id);
	}
	
	// Jdbc işlemleri.
	
	public List<Product> getAllProductsJdbc(){
		return jdbcProductDao.getAllProducts();
	}
	
	
	public Product findProductJdbc(int id) {
		return jdbcProductDao.findProduct(id);
	}

	
	public void saveProductJdbcT(Product p) {
		jdbcTemplateProductDao.saveProduct(p);
	}
	
	public void deleteProductJdbcT(int id) {
		jdbcTemplateProductDao.deleteProduct(id);
	}


}
