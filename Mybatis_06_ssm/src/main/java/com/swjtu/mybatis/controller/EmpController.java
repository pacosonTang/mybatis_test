package com.swjtu.mybatis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.swjtu.mybatis.bean.Employee;
import com.swjtu.mybatis.service.EmpService;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	
	@RequestMapping("/empsListQry")
	public String emps(@RequestParam("lastName") String lastName
			, Map<String, Object> map) {
		List<Employee> empList = empService.getEmps(lastName);
		map.put("EMP_LIST", empList);
		return "empList";
	}
}
