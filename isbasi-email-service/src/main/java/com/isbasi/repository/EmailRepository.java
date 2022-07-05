package com.isbasi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isbasi.model.Email;


public interface EmailRepository extends JpaRepository<Email,Integer> {// JPA kullanarak repo işlemlerini içten tanımlı fonksiyonlarla yapılabilir.
	
	

}
