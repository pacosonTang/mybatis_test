package com.swjtu.mybatis.dao;

import java.util.List;

import com.swjtu.mybatis.bean.Employee;

public interface EmployeeMapperPlus {
	
	/* 按照部门id 查询员工列表 */
	public List<Employee> getEmpListByDeptId(Integer deptId);
	
	// 使用association 分布查询 emp
	public Employee getEmpByIdStep(Integer id);
	
	// 查询员工以及部门
	public Employee getEmpAndDept(Integer id) ;
	
	public Employee getEmpByIdByResultType(Integer id);
	
	public Employee getEmpByIdByResultMap(Integer id);
}
