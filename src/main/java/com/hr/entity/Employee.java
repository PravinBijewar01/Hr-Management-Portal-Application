package com.hr.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="EMPLOYEE_NEME",length=100)
	@NotNull
	@Size(min=3,max=100,message="Employee name must be between 3 to 100 character")
	private String employeeName;
	
	@Email(message="plese provide valide email")
	@NotNull(message = "Email is required")
	@NotBlank(message = "Email is required")
	private String email;
	
	//@Pattern(regexp = "^()M | F$",message = "gender is required")
	//@NotNull(message = "Gender is required")
	//@NotBlank
	private String gender;
	
	//@Pattern(regexp = "^\\d(4)-\\d(2)-\\d(2)$", message = "Date of birth is required of this format")
	//@NotNull(message = "date of birth is required")
	private String dateOfBirth;
	
	 private LocalDate dob;
	 
	
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


	//@Pattern(regexp = "^\\d(4)-\\d(2)-\\d(2)$", message = "Joining Date is required of this format")
	//@NotNull(message = "Joining Date is required")
	private String joinDate;
	
	//@Pattern(regexp = "^[6-9]\\d(9)$",message = "Mobile number must be start with 6to9 and length be 10 digit")
	private String mobileNumber;
	
	//@Pattern(regexp = "^d(12)$",message = "Adharm number must be 12 char")
	private String aadhaarNumber;
	
	//@Pattern(regexp = "^d(9,18)$",message = "A/C number must be between 9 to 18 digit")
	private String accountNumber;
	  
	//@NotNull(message = "department name is required")
	//@Size(min=2,max=50,message = "department name must be between 2 to 50")
	//@NotBlank(message = "department name is required")
	private String department;
	
	private String designation;
	//@Size(min=2,max=50,message = "previous Company name must be between 2 to 50")
	private String previousCompany;
	
	//@Pattern(regexp = "^\\d(21)",message = "PF umber must be 22 char")
	private String pfNumber;
	
	private Double salary;
	//@Size(min=10,max=500,message = "current Address must be between 10 to 500 char")
	private String currentAddress;
	
	//@Size(min=10,max=500,message = "current Address must be between 10 to 500 char")
	private String permanrntAddress;
	
	//@NotNull(message = "Role is required")
	private String role;
	
	private boolean active=true;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	@UpdateTimestamp
	private LocalDateTime updateDate;
	
	private String password;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getPreviousCompany() {
		return previousCompany;
	}
	public void setPreviousCompany(String previousCompany) {
		this.previousCompany = previousCompany;
	}
	public String getPfNumber() {
		return pfNumber;
	}
	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getPermanrntAddress() {
		return permanrntAddress;
	}
	public void setPermanrntAddress(String permanrntAddress) {
		this.permanrntAddress = permanrntAddress;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", email=" + email + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + ", joinDate=" + joinDate + ", mobileNumber=" + mobileNumber
				+ ", aadhaarNumber=" + aadhaarNumber + ", accountNumber=" + accountNumber + ", department=" + department
				+ ", designation=" + designation + ", previousCompany=" + previousCompany + ", pfNumber=" + pfNumber
				+ ", salary=" + salary + ", currentAddress=" + currentAddress + ", permanrntAddress=" + permanrntAddress
				+ ", role=" + role + ", active=" + active + "]";
	}
	
/*	@CreatedDate
	@Column(name="created_at",nullable=false,updatable=false)
	private Date createdAt;
	
	@LastModifiedDate
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
*/
	
	
	

}
