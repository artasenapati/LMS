package com.eeft.lms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eeft.lms.dao.UserRepository;
import com.eeft.lms.object.LeaveRequest;
import com.eeft.lms.service.LMSService;

import jakarta.persistence.PostUpdate;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/lms")
public class LMSController {

	@Autowired
	private LMSService lmsService;
	
	@PostMapping("/saveLeaveDetails")
	public String saveLeaveDetails(@RequestBody LeaveRequest leaveRequest)
	{
		lmsService.saveLeaveRequest(leaveRequest);
		return "{\r\n"
		+ "   \"response\":\"success\"\r\n"
		+ "}";
		
	//	mailNotification(leaveRequest);
	}
	
	
	@PostMapping("/approveDeclineLeave")
	public String approveDeclineLeave(@RequestBody LeaveRequest leaveRequest)
	{
		lmsService.approveDeclineLeave(leaveRequest);
		return "{\r\n"
		+ "   \"response\":\"success\"\r\n"
		+ "}";
	}
	@DeleteMapping("/deleteLeaveDetail/{emp_id}")
	public String deleteLeaveDetail(@PathVariable("emp_id") int emp_id)
	{
		lmsService.deleteLeaveRequest(emp_id);
		return "{\r\n"
		+ "   \"response\":\"success\"\r\n"
		+ "}";
	}
	
	@GetMapping("/manageLeaves/{emp_id}")
	public List<LeaveRequest> manageLeaves(@PathVariable("emp_id") int emp_id)
	{
		return lmsService.getAllLeaveRequestbyId(emp_id).stream().collect(Collectors.toList());
	}
	
	@GetMapping("/pendingLeaves/{emp_id}")
	public List<LeaveRequest> pendingLeaves(@PathVariable("emp_id") int emp_id)
	{
		return lmsService.getAllPendingLeaveRequestbyApproverId(emp_id).stream().collect(Collectors.toList());
	}
	
//	@SuppressWarnings("null")
//	@PostMapping("/mailNotification")
	public String mailNotification( LeaveRequest leaveRequest,String action)
	{
		lmsService.mailNotification(leaveRequest,action);
		return "{\r\n"
		+ "   \"response\":\"success\"\r\n"
		+ "}";
	}
	
}
