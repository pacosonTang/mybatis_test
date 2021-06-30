package com.swjtu.mybatis.bean;

public class Employee {
	private Integer id;
	private String lastName;
	private String email;
	private String gender;
	private String deptId;
	private EmpStatus status = EmpStatus.LOGOUT;
	
	
	public EmpStatus getStatus() {
		return status;
	}
	public void setStatus(EmpStatus status) {
		this.status = status;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	public Employee(Integer id, String lastName, String email, String gender, String deptId) {
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.deptId = deptId;
	}
	
	public Employee(){}
	@Override
	public String toString() {
		return "id = " + id + ", lastName = " + lastName + ", email = " + email + ", gender = " + gender ;
	}
}
