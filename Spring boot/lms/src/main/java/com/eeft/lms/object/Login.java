package com.eeft.lms.object;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @Column(nullable = false, unique = true)
    private Long emp_id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String emp_name; 
    
    @Column(nullable = false)
    private String role;
    
    @Column(nullable = false)
    private String balance_el;
    
    @Column(nullable = false)
    private String balance_sl;
    
    @Column(nullable = false)
    private String emp_mail_id;
    
    @Column(nullable = false)
    private String Approver_Id;


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBalance_el() {
		return balance_el;
	}

	public void setBalance_el(String balance_el) {
		this.balance_el = balance_el;
	}

	public String getBalance_sl() {
		return balance_sl;
	}

	public void setBalance_sl(String balance_sl) {
		this.balance_sl = balance_sl;
	}

	public String getEmp_mail_id() {
		return emp_mail_id;
	}

	public void setEmp_mail_id(String emp_mail_id) {
		this.emp_mail_id = emp_mail_id;
	}

	public String getApprover_Id() {
		return Approver_Id;
	}

	public void setApprover_Id(String approver_Id) {
		Approver_Id = approver_Id;
	}

	public String getUsername() {
		return username;
	}

	public Long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    
}