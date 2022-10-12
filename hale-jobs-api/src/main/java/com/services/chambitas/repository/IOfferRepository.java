package com.services.chambitas.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.Offer;

public interface IOfferRepository extends JpaRepository<Offer, Long>{
	 
	Offer findOfferByConsecutive(String consecutive);
	
	@Query(value = "SELECT c.* FROM comments_offer AS c WHERE c.consecutive = :consecutive AND c.reg_borrado = 0",nativeQuery = true)
	Offer findOfferById(@Param("consecutive") String consecutive);
	
	@Query(value = "SELECT o.* FROM offer AS o WHERE o.user_id = :userId AND o.reg_borrado = 0",nativeQuery = true)
 	List<Offer> findOfferByUserMovil(@Param("userId") String userId);
	
	@Query(value = "SELECT o.* FROM offer AS o WHERE o.user_id = :userId AND o.reg_borrado = 0",nativeQuery = true)
 	Page<Offer> findOfferByUserWEB(@Param("userId") String userId, Pageable pageable);
	
	// Métodos para realizar busqueda
	@Query(value = "SELECT o.* FROM offer AS o WHERE o.user_id = :userId AND o.reg_borrado = 0",nativeQuery = true)
 	List<Offer> findOfferGeneralMovil(@Param("userId") String userId);
	
	@Query(value = "SELECT o.* FROM offer AS o WHERE o.user_id = :userId AND o.reg_borrado = 0",nativeQuery = true)
 	Page<Offer> findOfferGeneralWEB(@Param("userId") String userId, Pageable pageable);
	
	// Consulta para que el administrador pueda ver las solicitudes.
	@Query(value = "SELECT o.* FROM offer AS o WHERE o.user_id = :userId AND o.reg_borrado = 0",nativeQuery = true)
 	Page<Offer> findOfferAdmin(@Param("userId") String keyword, Pageable pageable);
	
}
