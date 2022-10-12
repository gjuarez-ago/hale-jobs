package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Payment;
import com.services.chambitas.domain.dto.PaymentDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IPaymentService {
	
	Payment generatePayment(PaymentDTO request);
	
	Page<Payment> getPaymentByLoanIdWA(int pageNo, int pageSize,String loanId);
	
	Page<Payment> getPaymentByLoanIdW(int pageNo, int pageSize,String loanId);
	
	List<Payment> getPaymentByLoanIdM(String loanId);
	
	Payment deletePayment(String consecutive, String currentUser) throws GenericException;
	
	Payment viewPaymentByConsecutive(String consecutive) throws GenericException;
	
	
}
