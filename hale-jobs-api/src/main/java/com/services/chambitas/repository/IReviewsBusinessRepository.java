package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.ReviewsBusiness;

public interface IReviewsBusinessRepository extends JpaRepository<ReviewsBusiness, Long>{
	
     ReviewsBusiness findReviewsBusinessByConsecutive(String consecutive);
     
     @Query(value = "SELECT rb.* FROM reviews_business AS rb WHERE rb.consecutive = :consecutive AND rb.reg_borrado = 0",nativeQuery = true)
     ReviewsBusiness findReviewsBusinessById(@Param("consecutive") String consecutive);

     @Query(value = "SELECT rb.* FROM reviews_business AS rb WHERE rb.user_client_id  = :userId  AND rb.reg_borrado = 0",nativeQuery = true)
 	 List<ReviewsBusiness> findReviewsBusinessByUserMovil(@Param("userId") String userId);
     
     @Query(value = "SELECT rb.* FROM reviews_business AS rb WHERE rb.user_client_id  = :userId  AND rb.reg_borrado = 0",nativeQuery = true)
     Page<ReviewsBusiness> findReviewsBusinessByUserWEB(@Param("userId") String userId, Pageable pageable);
     
     @Query(value = "SELECT rb.* FROM reviews_business AS rb WHERE rb.comments  = :keyword  AND rb.reg_borrado = 0",nativeQuery = true)
 	 Page<ReviewsBusiness> findReviewsBusinessAdmin(@Param("keyword") String keyword, Pageable pageable);
     
     @Query(value = "SELECT rb.* FROM reviews_business AS rb WHERE rb.offer_id = :offer_id AND rb.reg_borrado = 0",nativeQuery = true)
 	 List<ReviewsBusiness> findReviewsBusinessByOffer(@Param("offer_id") String offer_id);
          
}
