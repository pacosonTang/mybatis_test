package com.swjtu.mybatis.bean;

import java.io.Serializable;
import java.util.Map;

import org.apache.ibatis.type.Alias;

@Alias("emp")
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3363085364319002579L;
	private Integer id;
	private String lastName;
	private String email;
	private String gender;
	private Department depart;
	private Map<String, Object> map;
	
	public Department getDepart() {
		return depart;
	}
	public void setDepart(Department depart) {
		this.depart = depart;
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
	
	public Employee(Integer id, String lastName, String email, String gender) {
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
	}
	
	public Employee(Integer id, String lastName, String email, String gender, Department depart) {
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.depart = depart;
	}
	
	public Employee(){}
	@Override
	public String toString() {
		return "id = " + id + ", lastName = " + lastName + ", email = " + email + ", gender = " + gender
				+ ", dept = " + depart;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}
}
