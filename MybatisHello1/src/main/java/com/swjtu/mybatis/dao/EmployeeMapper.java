package com.swjtu.mybatis.dao;

import com.swjtu.mybatis.bean.Employee;

/*接口*/
public interface EmployeeMapper {
	
	/*查询employee id*/
	public Employee getEmployeeId(Integer id) ;
}
