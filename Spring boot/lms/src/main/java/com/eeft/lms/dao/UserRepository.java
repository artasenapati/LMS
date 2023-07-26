package com.eeft.lms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eeft.lms.object.LeaveRequest;
import com.eeft.lms.object.Login;

@Repository
public interface UserRepository extends JpaRepository<Login, String> {

	Login findByUsername(String username);
	
	@Query(value = "SELECT * FROM login l WHERE l.emp_id=:emp_id" ,nativeQuery = true)
	Login userDetails(@Param("emp_id") String emp_id);
	
	
}