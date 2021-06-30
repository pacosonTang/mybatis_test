package com.swjtu.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.swjtu.mybatis.bean.Employee;
import com.swjtu.mybatis.bean.MaxKey;

/*接口*/
public interface EmployeeMapper {
	
	/*统计emp_tbl表主键id的最大值*/
	public MaxKey getMaxKey(); 
	
	/*批量添加员工列表*/
	public int addEmpListBatch(List<Employee> list);
	
	/*添加员工*/
	public int addEmp(Employee e);
	
	/*查询员工列表*/
	public List<Employee> getEmpList(@Param("DEPT_ID") int deptId);
	
	/*查询employee id*/
	public Employee getEmployeeId(Integer id) ;
}
