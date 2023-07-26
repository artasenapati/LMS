package com.eeft.lms.object;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leaveRequest")
public class LeaveRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lr_id;
	
	@Column(nullable =  false)
	private String emp_id;
	
	@Column(nullable =  false)
	private String leave_type;
	
	@Column(nullable =  false)
	private String from_date;
	
	@Column(nullable =  false)
	private String to_date;
	
	@Column(nullable =  false)
	private String reason;
	
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false)
	private int el_count;
	
	
	
	public int getEl_count() {
		return el_count;
	}

	public void setEl_count(int el_count) {
		this.el_count = el_count;
	}

	public int getSl_count() {
		return sl_count;
	}

	public void setSl_count(int sl_count) {
		this.sl_count = sl_count;
	}

	public String getApprover_id() {
		return approver_id;
	}

	public void setApprover_id(String approver_id) {
		this.approver_id = approver_id;
	}

	@Column(nullable = false)
	private int sl_count;
	
	@Column(nullable = false)
	private String approver_id;

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLr_id() {
		return lr_id;
	}

	public void setLr_id(int lr_id) {
		this.lr_id = lr_id;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getLeave_type() {
		return leave_type;
	}

	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "LeaveRequest [lr_id=" + lr_id + ", emp_id=" + emp_id + ", leave_type=" + leave_type + ", from_date="
				+ from_date + ", to_date=" + to_date + ", reason=" + reason + ", status=" + status + "]";
	}
	
	
}
