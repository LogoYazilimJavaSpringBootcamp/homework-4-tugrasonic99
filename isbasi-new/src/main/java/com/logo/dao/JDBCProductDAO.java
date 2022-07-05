package com.logo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.logo.model.Product;

@Repository
public class JDBCProductDAO implements ProductDAO {// JDBC, kurduğu bağlantı sayesinde gönderdiği direkt query'lerle CRUD operasyonunu yapar.
	

	Connection con = null;// Bağlantı sayesinde uygulama ve db ile görüşme yapılır.

	@SuppressWarnings("finally")
	@Override
	public Product findProduct(int id) {// Gönderilen query ile aranan product çekilecek. Çekilen data resultset tarafından tutulup Product objesine çevrilecek. 
		String query = "SELECT * FROM products WHERE id=?";
		Product p=new Product();
		try {// Bağlantı bu dent içerisinde açılıp işlemler bittikten sonra kapatılır.
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/isbasi", "postgres", "123");
			PreparedStatement statement;
			statement = con.prepareStatement(query);
			// Değer yerleştirme.
			statement.setInt(1,id);
			ResultSet result=statement.executeQuery();
			
			while(result.next()) {
				// Product objesine dönüşüm.
				p.setId(result.getInt("id"));
				p.setName(result.getString("name"));
				p.setPrice(result.getDouble("price"));
				
			}
			
			System.out.println("JDBC Product Found");
			
			con.close();
			
		}
		catch(Exception e) {
			
		}
		finally {// Product döndürülür.
			return p;
		}

	}

	@SuppressWarnings("finally")
	@Override
	public List<Product> getAllProducts() {// Gönderilen query ile tüm product değerleri çekilecek. Çekilen data resultset tarafından tutulup Product listesine çevrilecek. 
		String query = "SELECT * FROM products";
		List<Product> list=new ArrayList<Product>();
		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/isbasi", "postgres", "123");
			Statement statement=con.createStatement();;
			ResultSet result=statement.executeQuery(query);
			
			while(result.next()) {// Değeler listeye eklenir.
				Product p=new Product();
				p.setId(result.getInt("id"));
				p.setName(result.getString("name"));
				p.setPrice(result.getDouble("price"));
				list.add(p);
				
			}
			
			
			con.close();
			System.out.println("JDBC Products Listed");
			
		}
		catch(Exception e) {
			
		}
		
		finally {// Liste döndürülür.
			return list;
		}
	}

	@Override
	public void deleteProduct(int id) {// Data silme query'si. id alan query ile bağlantı tarafından gönderilir.
		String query = "DELETE FROM products WHERE id=?";
		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/isbasi", "postgres", "123");
			PreparedStatement statement;
			statement = con.prepareStatement(query);
			statement.setInt(1,id);
			statement.executeUpdate(); 
			con.close();
			System.out.println("JDBC Product Deleted");
			
		}
		catch(Exception e) {
			
		}

	}

	@Override
	public void saveProduct(Product p) {// Data ekleme query'si.
		String query = "INSERT INTO products VALUES (?,?,?)";
		int idVal=getMaxValue();
		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/isbasi", "postgres", "123");
			PreparedStatement statement;
			statement = con.prepareStatement(query);
			statement.setInt(1, idVal);
			statement.setString(2, p.getName());
			statement.setDouble(3, p.getPrice());//
			statement.executeUpdate(); 
			System.out.println("JDBC Product Saved");
			con.close();

			
		}
		catch(Exception e) {
			
		}

	}
	
	public int getMaxValue() {
		// Hibernate tarafından hazırlanan tablonun ardışık max id değerini çekmek adına kullanılır.
		// Ana sebebi Hibernate'in ardışık id değerine direkt olarak bağlanmaya izin vermemesidir.
		int idVal=0;
		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/isbasi", "postgres", "123");
			PreparedStatement stat;
			ResultSet rs;
			String sql = "SELECT MAX(id) AS max_id FROM products";
			stat = con.prepareStatement(sql);
			rs = stat.executeQuery();
			if (rs.next()) {
				idVal = rs.getInt("max_id") + 1;
			}
			rs.close();
			con.close();
		}
		
		catch(Exception e) {
			
		}
		finally {
			return idVal+1;
		}
	}

}
