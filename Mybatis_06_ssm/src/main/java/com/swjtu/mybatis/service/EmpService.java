package com.swjtu.mybatis.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.mybatis.bean.Employee;
import com.swjtu.mybatis.dao.EmployeeMapper;

@Service
public class EmpService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private SqlSession sqlSession; // 批量操作的sqlSession
	
	public List<Employee> getEmps(String lastName) {
		employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
		lastName += "%"; 
		return employeeMapper.getEmpsByLastName(lastName);
	}
}
