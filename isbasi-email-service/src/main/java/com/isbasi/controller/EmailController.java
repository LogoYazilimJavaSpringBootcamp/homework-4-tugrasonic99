package com.isbasi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isbasi.model.Email;
import com.isbasi.service.EmailService;

@RestController
@RequestMapping(value = "/emails")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	
	@GetMapping
	public List<Email> findAll(){// GetMapping ile emails mapping altında tüm email değerlerini çeker.
		return emailService.findAllEmails();
		
	}

}
