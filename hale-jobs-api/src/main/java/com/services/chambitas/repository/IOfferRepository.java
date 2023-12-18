package com.services.chambitas.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.Offer;

public interface IOfferRepository extends JpaRepository<Offer, Long>{
	 
	@Query(value = "SELECT * FROM offer AS c WHERE c.id = :id AND c.reg_borrado = 0",nativeQuery = true)
	Offer findOfferById(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM offer AS c WHERE c.consecutive = :consecutive AND c.reg_borrado = 0",nativeQuery = true)
	Offer findOfferByConsecutive(@Param("consecutive") String consecutive);
	
	@Query(value = "SELECT o.* FROM offer AS o WHERE o.user_id = :userId AND o.reg_borrado = 0",nativeQuery = true)
 	List<Offer> findOfferByUserMovil(@Param("userId") Long userId);
	
	@Query(value = "SELECT o.* FROM offer AS o \n"
			+ "INNER JOIN job_subcategory AS j ON j.id = o.subcategory_id\n"
			+ "INNER JOIN level_study AS l ON l.id = o.level_study_id\n"
			+ "WHERE o.user_id = :userId AND o.category_id = 1 AND o.status = :status\n"
			+ "AND j.valor LIKE %:subcategory% AND l.clave LIKE %:level_study% AND o.title LIKE %:title% AND o.work_place LIKE %:work_place% AND o.urgency LIKE %:urgency% AND o.reg_borrado = 0",nativeQuery = true)
 	Page<Offer> findOfferByUserWEB(@Param("userId") Long userId, @Param("status") int status, @Param("subcategory") String subcategory, @Param("work_place") String workPlace, @Param("urgency") String urgency,@Param("level_study") String levelStudy,@Param("title")  String title,  Pageable pageable);
	
	// Métodos para realizar busqueda
	@Query(value = "SELECT o.* FROM offer AS o WHERE o.user_id = :title AND o.reg_borrado = 0",nativeQuery = true)
 	List<Offer> findOfferGeneralMovil(@Param("title") String title);
	
	@Query(value = "SELECT o.* FROM offer AS o WHERE o.title = :title AND o.reg_borrado = 0",nativeQuery = true)
 	Page<Offer> findOfferGeneralWEB(@Param("title") String title, Pageable pageable);
	
	// Consulta para que el administrador pueda ver las solicitudes.
	@Query(value = "SELECT o.* FROM offer AS o WHERE o.user_id = :title AND o.reg_borrado = 0",nativeQuery = true)
 	Page<Offer> findOfferAdmin(@Param("title") String title, Pageable pageable);
	
}
