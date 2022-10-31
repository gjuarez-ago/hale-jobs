package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.services.chambitas.domain.Permission;
import com.services.chambitas.domain.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
    
    User findUserByTokenAndUsername(String token, String username);
       
    User findUserByConsecutive(String consecutive);

    User findUserById(String id);
  
    @Query(value = "SELECT * FROM user u WHERE u.username LIKE %:username% AND u.names LIKE %:names% AND u.surnames LIKE %:surnames%",nativeQuery = true)
	Page<User> searchByFilters(@Param("username") String username,@Param("names") String names,@Param("surnames") String surnames, Pageable pageable);
	
	
	@Query(value = "SELECT u.permissions FROM User as u WHERE u.username = ?1")
	List<Permission> getPermissionsByUserName(String username);


}

