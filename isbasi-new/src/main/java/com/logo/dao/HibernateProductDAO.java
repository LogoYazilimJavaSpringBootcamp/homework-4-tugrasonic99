package com.logo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.logo.model.Product;

import org.hibernate.Session;
import org.hibernate.query.Query;

@Repository
public class HibernateProductDAO implements ProductDAO {// Hibernate CRUD operasyonlarının yapılacağı DAO sınıfı
	
	
	
	private EntityManager entityManager;// Hibernate, Entity annotation'la kurulmuş modellerin CRUD operasyonlarını Entity manager ile yapar.
	
	@Autowired
	public HibernateProductDAO(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public Product findProduct(int id) {// Verilen id ile product bulur.
		
		
	
		
		Session currentSession = entityManager.unwrap(Session.class);
		Product found=currentSession.get(Product.class, id);
		System.out.println("Hibernate Product Found");
		
		return found;
	}

	@Override
	public List<Product> getAllProducts() {// Tüm product'ları alır.
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("from Product", Product.class);
		List productList=query.getResultList();
		
		System.out.println("Hibernate Product Listed");
		
		return productList;
	}

	@Override
	public void deleteProduct(int id) {// Verilen id'ye göre product siler.
	
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("delete from Product where id=:pid");
		query.setParameter("pid", id);// Yer tutan parametre burada verilen değer ile belirlenir.
		
		query.executeUpdate();
		System.out.println("Hibernate Product Deleted");

	}

	@Override
	public void saveProduct(Product p) {// Verilen product DB'ye kaydedilir.
		// TODO Auto-generated method stub
        Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(p);
		System.out.println("Hibernate Product Saved");
		

	}

}
