package com.swjtu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.swjtu.mybatis.bean.Department;
import com.swjtu.mybatis.bean.Employee;
import com.swjtu.mybatis.dao.EmployeeMapperDynamicSQL;

public class MybatisTest4 {
	
	/**
	 *  使用  sql标签 插入员工列表 
	 */
	@Test
	public void addEmpListByForeachAndSql(){
		SqlSessionFactory factory = this.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
			int insertRows = mapper.addEmpListByForeachAndSql(
					Arrays.asList(new Employee[]{
							new Employee(null, "周杰伦sql", "zjl@google.com", "m", new Department(4)),
							new Employee(null, "蔡依林sql", "cyl@google.com", "f", new Department(4)), 
							new Employee(null, "林俊杰sql", "ljj@google.com", "m", new Department(3))
					}));  
			session.commit();
			System.out.println("insertRows = " + insertRows);
		} finally {
			session.close();
		}
	}
	
	/**
	 *  使用  bind标签 查询员工列表  
	 */
	@Test
	public void getEmpListByBind(){
		SqlSessionFactory factory = this.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
			List<Employee> empList = mapper.getEmpListByBind("tangrong%");
			session.commit();
			
			for (Employee e : empList) {
				System.out.println(e); 
				System.out.println("=======================================");
			}
		} finally {
			session.close();
		}
	}
	
	/**
	 *  使用  mybatis内置参数 查询员工列表  
	 */
	@Test
	public void getEmpListByInnerParameter(){
		SqlSessionFactory factory = this.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
			List<Employee> empList = mapper.getEmpListByInnerParameter(
					Arrays.asList(new Integer[]{1, 2, 3}));  
			session.commit();
			
			for (Employee e : empList) {
				System.out.println(e); 
				System.out.println("=======================================");
			}
		} finally {
			session.close();
		}
	}
	
	
	/**
	 *  使用foreach标签插入员工列表 
	 */
	@Test
	public void addEmpListByForeach(){
		SqlSessionFactory factory = this.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
			int insertRows = mapper.addEmpListByForeach(
					Arrays.asList(new Employee[]{
							new Employee(null, "周杰伦", "zjl@google.com", "m", new Department(1)),
							new Employee(null, "蔡依林", "cyl@google.com", "f", new Department(2)), 
							new Employee(null, "林俊杰", "ljj@google.com", "m", new Department(2))
					}));  
			session.commit();
			System.out.println("insertRows = " + insertRows);
		} finally {
			session.close();
		}
	}
	
	
	/**
	 *  使用foreach标签查询员工列表
	 */
	@Test
	public void getEmpListByConditionForeach(){
		SqlSessionFactory factory = this.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
			List<Employee> empList = mapper.getEmpListByConditionForeach(
					Arrays.asList(new Integer[]{1, 2, 3}));  
			for (Employee e : empList) {
				System.out.println(e); 
				System.out.println("=======================================");
			}
		} finally {
			session.close();
		}
	}
	
	/**
	 *  根据查询条件查询员工信息-set标签
	 */
	@Test
	public void getEmpListByConditionSet(){
		SqlSessionFactory factory = this.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
			Map<String, Object> map = new HashMap<>();
			map.put("ID", 1);
//			map.put("LAST_NAME", "tangrong2017");
			map.put("EMAIL", "tangrong@google.com");
//			map.put("GENDER", "F");
			int updRows = mapper.updEmp(map);  
			session.commit(); // 手工提交
			System.out.println("updRows = " + updRows);
		} finally {
			session.close();
		}
	}
	
	/**
	 *  根据查询条件查询员工信息-choose标签
	 */
	@Test
	public void getEmpListByConditionChoose() throws IOException{
		SqlSessionFactory factory = this.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
		Map<String, Object> map = new HashMap<>();
		map.put("ID", null);
		map.put("LAST_NAME", "tangrong%");
		map.put("EMAIL", "");
		map.put("GENDER", "");
		List<Employee> empList = mapper.getEmpListByConditionChoose(map);  

		for (Employee e : empList) {
			System.out.println(e);
			System.out.println("=======================================");
		}
	}
	
	/**
	 *  根据查询条件查询员工信息
	 */
	@Test
	public void getEmpListByConditionTrim() throws IOException{
		SqlSessionFactory factory = this.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
		Map<String, Object> map = new HashMap<>();
		map.put("ID", "1");
		map.put("LAST_NAME", "");
		map.put("EMAIL", "");
		map.put("GENDER", "");
		List<Employee> empList = mapper.getEmpListByConditionTrim(map);  

		for (Employee e : empList) {
			System.out.println(e);
			System.out.println("=======================================");
		}
	}
	
	/**
	 *  根据查询条件查询员工信息
	 */
	@Test
	public void getEmpListByConditionIf() throws IOException{
		SqlSessionFactory factory = this.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
		Map<String, Object> map = new HashMap<>();
		map.put("ID", "1");
		map.put("LAST_NAME", "");
		map.put("EMAIL", "");
		map.put("GENDER", "");
		List<Employee> empList = mapper.getEmpListByConditionIf(map);  

		for (Employee e : empList) {
			System.out.println(e);
			System.out.println("=======================================");
		}
	}
	/**
	 * 获取SqlSessionFactory 
	 * @return
	 */
	public SqlSessionFactory getSqlSessionFactory() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try { 
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			return sqlSessionFactory;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
