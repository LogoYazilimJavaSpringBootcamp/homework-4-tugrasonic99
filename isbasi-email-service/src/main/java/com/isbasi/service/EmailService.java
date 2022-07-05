package com.isbasi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isbasi.model.Email;
import com.isbasi.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	
	public List<Email> findAllEmails(){
		return emailRepository.findAll();
	}
	
	

}
