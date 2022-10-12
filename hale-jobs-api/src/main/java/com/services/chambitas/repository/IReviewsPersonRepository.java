package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.ReviewsPerson;

public interface IReviewsPersonRepository extends JpaRepository<ReviewsPerson, Long> {

	ReviewsPerson findReviewsPersonByConsecutive(String consecutive);

	@Query(value = "SELECT rp.* FROM reviews_person AS rp WHERE rp.consecutive = :consecutive AND rp.reg_borrado = 0", nativeQuery = true)
	ReviewsPerson findReviewsPersonById(@Param("consecutive") String consecutive);

	@Query(value = "SELECT rp.* FROM reviews_person AS rp WHERE rp.user_client_id  = :userId  AND rp.reg_borrado = 0", nativeQuery = true)
	List<ReviewsPerson> findReviewsPersonByUserMovil(@Param("userId") String userId);

	@Query(value = "SELECT rp.* FROM reviews_person AS rp WHERE rp.user_client_id  = :userId  AND rp.reg_borrado = 0", nativeQuery = true)
	Page<ReviewsPerson> findReviewsPersonByUserWEB(@Param("userId") String userId, Pageable pageable);

	@Query(value = "SELECT rp.* FROM reviews_person AS rp WHERE rp.comments  = :keyword  AND rp.reg_borrado = 0", nativeQuery = true)
	Page<ReviewsPerson> findReviewsBusinessAdmin(@Param("keyword") String keyword, Pageable pageable);

	@Query(value = "SELECT rp.* FROM reviews_person AS rp WHERE rp.offer_id = :offer_id AND rp.reg_borrado = 0", nativeQuery = true)
	List<ReviewsPerson> findReviewsPersonByOffer(@Param("offer_id") String offer_id);

}
