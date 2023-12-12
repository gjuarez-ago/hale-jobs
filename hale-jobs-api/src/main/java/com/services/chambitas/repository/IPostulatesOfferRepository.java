package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.PostulatesOffer;

public interface IPostulatesOfferRepository extends JpaRepository<PostulatesOffer, Long>{
	
	 PostulatesOffer findPostulatesOfferByConsecutive(String consecutive);
	
	 @Query(value = "SELECT po.* FROM postulates_offer AS po WHERE po.consecutive = :consecutive AND po.reg_borrado = 0",nativeQuery = true)
	 PostulatesOffer findPostulateById(@Param("consecutive") String consecutive);
	     
	 @Query(value = "SELECT po.* FROM postulates_offer AS po WHERE po.user_id = :userId AND po.reg_borrado = 0",nativeQuery = true)
	 List<PostulatesOffer> findPostulatesByUserMovil(@Param("userId") Long userId);
	 
	 @Query(value = "SELECT po.* FROM postulates_offer AS po WHERE po.user_id = :userId AND po.reg_borrado = 0",nativeQuery = true)
	 Page<PostulatesOffer> findPostulatesByUserWEB(@Param("userId") Long userId, Pageable pageable);
	 
	 @Query(value = "SELECT po.* FROM postulates_offer AS po WHERE po.offer_id = :offerId AND po.reg_borrado = 0",nativeQuery = true)
	 List<PostulatesOffer> findPostulatesByOfferMovil(@Param("offerId") Long offerId);
	 
	 @Query(value = "SELECT po.* FROM postulates_offer AS po WHERE po.offer_id = :offerId AND po.reg_borrado = 0",nativeQuery = true)
	 Page<PostulatesOffer> findPostulatesByOfferWEB(@Param("offerId") Long offerId,Pageable pageable);
	 
	 @Query(value = "SELECT po.* FROM postulates_offer AS po WHERE po.offer_id = :offerId AND po.reg_borrado = 0",nativeQuery = true)
	 List<PostulatesOffer> findPostulatesAdmin(@Param("offerId") Long offerId, Pageable pageable);
	 
	 @Query(value = "SELECT po.* FROM postulates_offer AS po WHERE po.user_id = :userId AND po.offer_id = :offerId AND po.reg_borrado = 0",nativeQuery = true)
	 PostulatesOffer findPostulateByUserAndOffer(@Param("userId") Long userId, @Param("offerId") Long offerId);

}
