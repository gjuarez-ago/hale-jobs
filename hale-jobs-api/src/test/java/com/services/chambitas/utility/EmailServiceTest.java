package com.services.chambitas.utility;

import javax.mail.MessagingException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
	@Autowired
	private EmailService emailService;

	@Test
	public void sendNewPasswordEmail() throws MessagingException {
		String firstName = "abc";
		String password = "abc";
		String email = "abc";
		emailService.sendNewPasswordEmail(firstName, password, email);
	}
}
