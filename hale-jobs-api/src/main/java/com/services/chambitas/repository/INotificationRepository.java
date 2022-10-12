package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.Notification;

public interface INotificationRepository extends JpaRepository<Notification, Long>{
	
   Notification findNotificationByConsecutive(String consecutive);
   
   @Query(value = "SELECT n.* FROM notification AS n WHERE n.consecutive = :consecutive AND n.reg_borrado = 0",nativeQuery = true)
   Notification findNotificationById(@Param("consecutive") String consecutive); 
   
   @Query(value = "SELECT n.* FROM notification AS n WHERE n.email_destination = :email AND n.reg_borrado = 0",nativeQuery = true)
   List<Notification> findNotificationByUserMovil(@Param("email") String email);
   
   @Query(value = "SELECT n.* FROM notification AS n WHERE n.email_destination = :email AND n.reg_borrado = 0",nativeQuery = true)
   Page<Notification> findNotificationByUserWEB(@Param("email") String email, Pageable pageable);
   
   @Query(value = "SELECT n.* FROM notification AS n WHERE n.reg_borrado = 0",nativeQuery = true)
   Page<Notification> findNotificationByAdmin(Pageable pageable);
   
}
