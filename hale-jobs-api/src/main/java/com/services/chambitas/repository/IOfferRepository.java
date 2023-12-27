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
	
	@Query(value = "SELECT o FROM Offer o JOIN o.user u JOIN o.category c JOIN o.subcategory s JOIN o.rangeAmount r JOIN o.levelStudy l JOIN o.typeOfJob t WHERE u.id = :userId AND c.id = 1 AND o.title LIKE %:title% AND s.valor LIKE %:subcategory% AND r.clave LIKE %:range_amount% AND o.status = :status AND o.urgency LIKE %:urgency% AND o.workPlace LIKE %:work_place% AND l.clave LIKE %:level_study% AND t.clave LIKE %:type_job% AND o.regBorrado = 0")
 	List<Offer> findOfferByUserMovil(@Param("userId") Long userId, @Param("title") String title, @Param("subcategory") String subcategory, @Param("range_amount") String rangeAmount,@Param("urgency") String urgency, @Param("work_place") String workPlace,@Param("level_study") String levelStudy, @Param("type_job") String typeJob, @Param("status") int status);
	
	@Query(value = "SELECT o FROM Offer o JOIN o.user u JOIN o.category c JOIN o.subcategory s JOIN o.rangeAmount r JOIN o.levelStudy l JOIN o.typeOfJob t WHERE u.id = :userId AND c.id = 1 AND o.title LIKE %:title% AND s.valor LIKE %:subcategory% AND r.clave LIKE %:range_amount% AND o.status = :status AND o.urgency LIKE %:urgency% AND o.workPlace LIKE %:work_place% AND l.clave LIKE %:level_study% AND t.clave LIKE %:type_job% AND o.regBorrado = 0")
 	List<Offer> findOfferByUserWEB(@Param("userId") Long userId, @Param("title") String title, @Param("subcategory") String subcategory, @Param("range_amount") String rangeAmount,@Param("urgency") String urgency, @Param("work_place") String workPlace,@Param("level_study") String levelStudy, @Param("type_job") String typeJob, @Param("status") int status);
	
	// Métodos para realizar busqueda
	@Query(value = "SELECT o.* FROM offer AS o WHERE o.user_id = :title AND o.reg_borrado = 0",nativeQuery = true)
 	List<Offer> findOfferGeneralMovil(@Param("title") String title);
	
	@Query(value = "SELECT o.* FROM offer AS o WHERE o.title = :title AND o.reg_borrado = 0",nativeQuery = true)
 	Page<Offer> findOfferGeneralWEB(@Param("title") String title, Pageable pageable);
	
	// Consulta para que el administrador pueda ver las solicitudes.
	@Query(value = "SELECT o.* FROM offer AS o WHERE o.user_id = :title AND o.reg_borrado = 0",nativeQuery = true)
 	Page<Offer> findOfferAdmin(@Param("title") String title, Pageable pageable);
	
}
