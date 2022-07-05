package com.isbasi.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isbasi.dto.EmailDto;
import com.isbasi.model.Email;
import com.isbasi.repository.EmailRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailListener {
	
	@Autowired
	private EmailRepository emailRepository;

	@RabbitListener(queues = "isbasi.email")
	public void emailListener(EmailDto emailDto) {
		log.info("email address: {}", emailDto.getEmail());
		// RabbitMQ queue dan alınan değerler yeni bir email objesi olarak repo'ya kaydı sağlanır.
		Email newEmail=new Email(emailDto.getTo(),emailDto.getTitle(),emailDto.getEmail());
		emailRepository.save(newEmail);
	
	}

}
