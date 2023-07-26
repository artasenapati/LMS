package com.eeft.lms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eeft.lms.object.LeaveRequest;

@Repository
public interface LMSRepository extends JpaRepository<LeaveRequest, Integer>{
	
	@SuppressWarnings("unchecked")
	LeaveRequest save(LeaveRequest leaveRequest);
	
	@Query(value = "SELECT * FROM leave_request lr WHERE lr.emp_id =:emp_id and lr.status='Pending' ", nativeQuery = true)
	List<LeaveRequest> getAllLeaveRequestbyId(@Param("emp_id") int id);
	
	@Query(value = "SELECT * FROM leave_request lr  WHERE lr.approver_id =:emp_id and lr.status='Pending' ", nativeQuery = true)
	List<LeaveRequest> getAllPendingLeaveRequestbyApproverId(@Param("emp_id") int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE leave_request AS lr\r\n"
			+ "INNER JOIN login AS l ON lr.emp_id= l.emp_id\r\n"
			+ "SET l.balance_sl=cast(l.balance_sl AS INT)-cast(lr.SL_Count AS INT),l.balance_el=cast(l.balance_el AS INT)-cast(lr.EL_Count AS INT),lr.status=:status WHERE lr.emp_id =:emp_id AND lr.lr_id=:lr_id ", nativeQuery = true)
		
	 void approveDeclineLeave(@Param("emp_id") String id,@Param("lr_id") int lr_id,@Param("status") String status);
	@Query(value = "UPDATE leave_request AS lr\r\n"
			+ "SET lr.status=:status"
		
			+ " WHERE lr.emp_id =:emp_id AND lr.lr_id=:lr_id ", nativeQuery = true)
	void declineLeave(String emp_id, int lr_id, String status);

}
