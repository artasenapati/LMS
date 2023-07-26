package com.eeft.lms.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eeft.lms.dao.LMSRepository;
import com.eeft.lms.dao.UserRepository;
import com.eeft.lms.model.Mail;
import com.eeft.lms.object.LeaveRequest;
import com.eeft.lms.object.Login;

@Service
public class LMSServiceImpl implements LMSService{
	
	@Autowired
	private LMSRepository lmsRepository;
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailService;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public void saveLeaveRequest(LeaveRequest leaveRequest) {
		Login user = userRepository.userDetails(leaveRequest.getEmp_id());
		boolean proceed=false;
		if(leaveRequest.getLeave_type().equalsIgnoreCase("SL") && Integer.parseInt( user.getBalance_sl())>Integer.parseInt("0")) {
			proceed=true;
		}
		if(leaveRequest.getLeave_type().equalsIgnoreCase("EL") && Integer.parseInt( user.getBalance_el())>Integer.parseInt("0")) {
			proceed=true;
		}
		if(proceed) {
		LocalDate from_date = LocalDate.parse(leaveRequest.getFrom_date(), formatter);
		LocalDate to_date = LocalDate.parse(leaveRequest.getTo_date(), formatter);
		Period period = Period.between(from_date, to_date);
	    int days = period.getDays();
	    if(days==0)
	    	days=1;
	    if(leaveRequest.getLeave_type().equalsIgnoreCase("SL"))
	    	leaveRequest.setSl_count(days);
 	    if(leaveRequest.getLeave_type().equalsIgnoreCase("EL"))
	    	leaveRequest.setEl_count(days);
		lmsRepository.save(leaveRequest);
		mailNotification(leaveRequest,"Request");
		}
		
	}
	
	
	
	@Override
	public void approveDeclineLeave(LeaveRequest leaveRequest) {
		if(leaveRequest.getStatus().equalsIgnoreCase("Approve"))
		lmsRepository.approveDeclineLeave(leaveRequest.getEmp_id(),leaveRequest.getLr_id(),leaveRequest.getStatus());
		if(leaveRequest.getStatus().equalsIgnoreCase("Decline"))
		lmsRepository.declineLeave(leaveRequest.getEmp_id(),leaveRequest.getLr_id(),leaveRequest.getStatus());
		
		mailNotification(leaveRequest,leaveRequest.getStatus());
	}
	@Override
	public List<LeaveRequest> getAllLeaveRequestbyId(int emp_id) {
		return lmsRepository.getAllLeaveRequestbyId(emp_id);
	}

	//deleting a specific record by using the method deleteById() of CrudRepository  
	@Override
		public void deleteLeaveRequest(int id)   
		{  
			lmsRepository.deleteById(id);  
		}

	@Override
	public List<LeaveRequest> getAllPendingLeaveRequestbyApproverId(int emp_id) {
		return lmsRepository.getAllPendingLeaveRequestbyApproverId(emp_id);
	}

	@Override
	public void mailNotification(LeaveRequest leaveRequest,String action) {
		Login user = userRepository.userDetails(leaveRequest.getEmp_id());
		Login approverDetails = userRepository.userDetails(leaveRequest.getApprover_id());
		Mail mail = new Mail();
		if(action.equalsIgnoreCase("Request")) {
		mail.setMailTo(approverDetails.getEmp_mail_id());
		mail.setMailSubject(user.getEmp_name() +" "+"Leave Request");
		mail.setMailContent("Hi "+approverDetails.getEmp_name()+" sir,Please approve my leaves Thanks, "+user.getEmp_name());
		}
		
		if(action.equalsIgnoreCase("Approve")) {
			mail.setMailTo(user.getEmp_mail_id());
			mail.setMailSubject(user.getEmp_name() +" "+"Leave Request");
			mail.setMailContent("Hi "+user.getEmp_name()+" Your leave/s request have approved Thanks ");
		}
		if(action.equalsIgnoreCase("Decline")) {
			mail.setMailTo(user.getEmp_mail_id());
			mail.setMailSubject(user.getEmp_name() +" "+"Leave Request");
			mail.setMailContent("Hi "+user.getEmp_name()+" Your leave/s request have declined Thanks ");
		}
		mailService.sendEmail(mail);
	}
	
	
}
