package com.swjtu.mybatis.dao;

import org.apache.ibatis.annotations.Select;

import com.swjtu.mybatis.bean.Employee;

public interface EmployeeMapperAnnotation {
	
	/*查询employee id*/
	@Select("SELECT id AS ID"
			+ ", last_name AS LASTNAME"
			+ ", email AS EMAIL"
			+ ", gender AS GENDER "
			+ "FROM emp_tbl "
			+ "WHERE id = #{ID}")
	public Employee getEmployeeId(Integer id) ;
}
