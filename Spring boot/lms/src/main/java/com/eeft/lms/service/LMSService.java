package com.eeft.lms.service;

import java.util.List;

import com.eeft.lms.dao.UserRepository;
import com.eeft.lms.object.LeaveRequest;

public interface LMSService {
	
	public void saveLeaveRequest(LeaveRequest leaveRequest);
	
	public void mailNotification(LeaveRequest leaveRequest,String action);
	
	public List<LeaveRequest> getAllLeaveRequestbyId(int emp_id);
	
	public List<LeaveRequest> getAllPendingLeaveRequestbyApproverId(int emp_id);
	
	public void deleteLeaveRequest(int id) ;
	public void approveDeclineLeave(LeaveRequest leaveRequest) ;
	

}
