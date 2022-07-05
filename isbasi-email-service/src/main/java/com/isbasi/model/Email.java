package com.isbasi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emails")
public class Email {// Email modelini tabloya çevirebilmek adına Entity ve Table annotation'lar kullanıldı.
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;// Primary key
	
	// Tablo ana değeler.
	@Column
	private String receiver;
	@Column
	private String title;
	@Column
	private String email;
	
	
	
	public Email() {}
	
	
	public Email(String receiver, String title, String email) {
		super();
		this.receiver = receiver;
		this.title = title;
		this.email = email;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
