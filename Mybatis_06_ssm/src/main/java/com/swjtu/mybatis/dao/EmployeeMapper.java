package com.swjtu.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.swjtu.mybatis.bean.Employee;

/*接口*/
public interface EmployeeMapper {
	/* 通过 id 查询employee */
	public Employee getEmployeeById(Integer id) ;
	
	/* 通过 lastName 查询 员工列表*/
	public List<Employee> getEmpsByLastName(@Param("LAST_NAME") String lastName) ;
}
