package com.logo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.logo.model.Product;

@Repository
public class JDBCTemplateProductDAO implements ProductDAO {// JdbcTemplate ile gönderilen query'ler sayesinde CRUD operasyonları yapılır.
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Product findProduct(int id) {// Gönderilen query ile product çekilir.
		String q = "SELECT * from tutorials WHERE id ILIKE '%" + id + "%'";
		System.out.println("JDBCTemplate Product Found");
	    return (Product) jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Product.class));
	}

	@Override
	public List<Product> getAllProducts() {// Tüm tablonun döndürüldüğü query.
		System.out.println("JDBCTemplate Products Listed");
		return jdbcTemplate.query("SELECT * from products", BeanPropertyRowMapper.newInstance(Product.class));
	}

	@Override
	public void deleteProduct(int id) {// Id'ye bağlı olarak silen query.
		jdbcTemplate.update("DELETE FROM products WHERE id=?", id);
		System.out.println("JDBCTemplate Product Deleted");

	}

	@Override
	public void saveProduct(Product p) {// Product'ı tabloya kaydeden query.
		Integer i=jdbcTemplate.queryForObject("SELECT MAX(id) FROM products",Integer.class);
		// Yukarıdaki ilk query, ardışık olarak giden hibernate entity tarafından hazırlanan tablo'nun id'nin max değerini alir. 
		//Bunun ana sebebi hibernate'in ardışık id değerini tabloya girerken üretmesidir.
		// Bu yüzden hibernate dışında bir data girilirken hibernate'in almadığı bir pk girilmesi gerekiyor.
		int iHold=Integer.parseInt(i.toString());
		iHold++;// Max id değeri 1 arttırılıp insert'te kullanılacak.
		jdbcTemplate.update("INSERT INTO products (id,name,price) VALUES(?,?,?)",
		        new Object[] {iHold, p.getName(),p.getPrice()});
		System.out.println("JDBCTemplate Product Saved");
		
		  }
	     

	}


