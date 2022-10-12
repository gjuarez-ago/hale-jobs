package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.services.chambitas.domain.Payment;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment,Long>{
	
	Payment findPaymentByConsecutive(String consecutive);
	
	@Query(value = "SELECT p.* FROM payment AS p INNER JOIN loan AS l ON l.id = p.prestamo_id AND l.reg_borrado = 0 WHERE l.consecutive = :loanId AND p.reg_borrado = 0",nativeQuery = true)
	List<Payment> findPaymentsByLoanIdM(@Param("loanId") String loanId);
	
//	@Query(value = "SELECT p.* FROM payment AS p INNER JOIN loan AS l ON l.id = p.prestamo_id AND l.reg_borrado = 0 WHERE l.consecutive = :loanId AND p.reg_borrado = 0",nativeQuery = true)
//	List<Payment> findPaymentsByLoanIdM(@Param("loanId") String loanId);
	
	@Query(value = "SELECT p.* FROM payment AS p INNER JOIN loan AS l ON l.id = p.prestamo_id AND l.reg_borrado = 0 WHERE l.consecutive = :loanId AND p.reg_borrado = 0",nativeQuery = true)
	Page<Payment> findPaymentsByLoanIdW(@Param("loanId") String loanId, Pageable pageable);	
	
	@Query(value = "SELECT p.* FROM payment AS p INNER JOIN loan AS l ON l.id = p.prestamo_id AND l.reg_borrado = 0 WHERE l.consecutive LIKE %:loanId% AND p.reg_borrado = 0",nativeQuery = true)
	Page<Payment> findPaymentsByLoanIdWA(@Param("loanId") String loanId, Pageable pageable);
		
}