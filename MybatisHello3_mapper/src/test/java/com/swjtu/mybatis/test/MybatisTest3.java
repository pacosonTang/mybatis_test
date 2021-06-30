package com.swjtu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
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
import com.swjtu.mybatis.dao.DepartMapper;
import com.swjtu.mybatis.dao.EmployeeMapper;
import com.swjtu.mybatis.dao.EmployeeMapperAnnotation;
import com.swjtu.mybatis.dao.EmployeeMapperPlus;

public class MybatisTest3 {
	
	/**
	 *  根据部门id 查询员工列表
	 *  通过 discriminator 标签判断字段值实现不同的查询和赋值逻辑
	 *  1.女生：就把部门信息查询出来（部门编号全部为2）
	 *  2.男生：把last_name的值赋值给 email（部门编号全部为1）
	 */
	@Test
	public void testGetEmpListByDeptId() throws IOException{
		SqlSessionFactory factory = this.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
		List<Employee> empList = mapper.getEmpListByDeptId(1);  
		
		for (Employee e : empList) {
			System.out.println(e);
			System.out.println("=======================================");
		}
	}
	
	
	/**
	 *  使用collection分步查询部门及其员工列表
	 */
	@Test
	public void testDeptAndEmpListByStepQry() throws IOException{
		SqlSessionFactory factory = this.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		DepartMapper mapper = session.getMapper(DepartMapper.class);
		Department dept = mapper.getDeptByIdByStep(2);  
		
		System.out.println("deptName =" + dept.getDeptName());
		/*System.out.println("================使用collection分步查询部门及其员工列表=====================");
		List<Employee> empList = dept.getEmps();
		for (Employee e : empList) {
			System.out.println(e);
		}*/
	}
	
	/**
	 *  查询部门及其员工列表
	 *  查询员工及其所在部门 
	 */
	@Test
	public void testDeptAndEmpListQry() throws IOException{
		SqlSessionFactory factory = this.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		DepartMapper mapper = session.getMapper(DepartMapper.class);
		Department dept = mapper.getDeptAndEmpsById(2);  
		System.out.println(dept);
		System.out.println("=====================================");
		List<Employee> empList = dept.getEmps();
		for (Employee e : empList) {
			System.out.println(e);
		}
	}
	
	/**
	 *  测试分布查询
	 *  查询员工及其所在部门 
	 */
	@Test
	public void testQryByStep() throws IOException{
		SqlSessionFactory factory = this.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
		Employee emp = mapper.getEmpByIdStep(15); // 分布查询
//		Employee emp = mapper.getEmpByIdByResultType(15);
		System.out.println(emp.getGender());
		System.out.println("================================");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(emp.getDepart());
	}
	
	/**
	 *  测试级联查询：
	 *  查询员工及其所在部门 
	 */
	@Test
	public void testAssociateQry() throws IOException{
		SqlSessionFactory factory = this.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
		Employee emp = mapper.getEmpAndDept(15);
//		Employee emp = mapper.getEmpByIdByResultType(15);
		System.out.println(emp);
	}
	
	/**
	 *  使用 resultMap 自定义结果集 
	 */
	@Test
	public void testResultMap() throws IOException{
		SqlSessionFactory factory = this.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
		Employee emp = mapper.getEmpByIdByResultMap(15);
//		Employee emp = mapper.getEmpByIdByResultType(15);
		System.out.println(emp);
	}
	
	/**
	 *  测试把查询结果封装到List<Map>
	 *  key 就是列名，value就是列的值 
	 */
	@Test
	public void testListMapQry() throws IOException{
		SqlSessionFactory factory = this.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		List<Map<String, Object>> list 
			= mapper.getEmpListByLastNameReturnListMap("tangrong%");
		
		for (Map<String, Object> map : list) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.print(entry.getKey() + ":" + entry.getValue() + ", ");
			}
			System.out.println();
		}
	}
	
	/**
	 *  测试返回一条记录的map
	 *  key 就是列名，value就是列的值 
	 */
	@Test
	public void testMapQry() throws IOException{
		SqlSessionFactory factory = this.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
//		Map<String, Object> map = mapper.getEmpByIdReturnMap(15);
		
		Map<String, Employee> map = mapper.getEmpByLastNameLikeReturnMap("tangrong%");
		
		for (Map.Entry<String, Employee> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
	/**
	 *  测试查询列表
	 */
	@Test
	public void testListQry() throws IOException{
		
		SqlSessionFactory factory = this.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		List<Employee> list = mapper.getEmpListByLastName("tangrong%");
		for (Employee e : list) {
			System.out.println(e);
		}
	}
	
	/**
	 * 多条件查询emp
	 * @throws IOException
	 */
	@Test
	public void testQueryByMultiCondition() throws IOException {
		System.out.println("testCRUD()");
		/*1. 获取 SqlSessionFactory 对象*/
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		
		/*2.获取SqlSession对象, 不会自动提交数据*/
		SqlSession session = sqlSessionFactory.openSession();
		try { 
			/* 获取sql映射对象 */
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
//			Employee e = mapper.getEmpByIdAndLastName(15, "tangrong");
			Map<String, Object> params = new HashMap<>(); 
			params.put("ID", 15); 
			params.put("LAST_NAME", "tangrong");
			params.put("TABLE_NAME", "emp_tbl"); 
			Employee e = mapper.getEmpByMap(params); 
			System.out.println(e);
		} finally { 
			session.close();
		}
		System.out.println("bingo!");
	}
	
	/**
	 * 测试增删改
	 * 1、mybatis运行增删改直接定义以下类型返回值：
	 * 		integer, Long, boolean, void`
	 * 2、我们需要手动提交数据：
	 * 		sqlSessionFactory.openSession(); 手动提交
	 * 	    sqlSessionFactory.openSession(true); 自动提交
	 * @throws IOException
	 */
	@Test
	public void testCRUD() throws IOException {
		System.out.println("testCRUD()");
		/*1. 获取 SqlSessionFactory 对象*/
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		
		/*2.获取SqlSession对象, 不会自动提交数据*/
		SqlSession session = sqlSessionFactory.openSession();
		try { 
			/* 获取sql映射对象 */
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			
			/* 1. 新增一个员工 */ 
			Employee e = new Employee(null, "tangrong", "tangrong@google.com", "M");
			mapper.addEmp(e);
			System.out.println("id = " + e.getId());
			session.commit();
			
			/* 更新一个员工 */
			/*Employee e = new Employee(14, "tangrong14", "tangrong14@google.com", "M");
			if (1 != mapper.updateEmp(e)) {
				System.out.println("error");
				return ;
			}
			session.commit();*/
			
			/* 删除一个员工 */
			/*int delRows = mapper.deleteEmp(14);
			if (1 != delRows) {
				System.out.println("error");
				return ;
			}*/
			session.commit();
		} finally {
			session.close();
		}
		System.out.println("bingo!");
	}
	
	
	/**
	 * 根据接口来获取sql映射
	 * @throws IOException
	 */
	@Test
	public void test3() throws IOException {
		System.out.println("test3()");
		/*1. 获取 SqlSessionFactory 对象*/
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		
		/*2.获取SqlSession对象*/
		SqlSession session = sqlSessionFactory.openSession();
		/* 获取sql映射对象 */
		EmployeeMapperAnnotation mapper = session.getMapper(EmployeeMapperAnnotation.class);
		/* 执行sql */ 
		Employee e = mapper.getEmployeeId(2);
		System.out.println(mapper.getClass());
		System.out.println(e);
	}
	
	/**
	 * 根据接口来获取sql映射
	 * @throws IOException
	 */
	@Test
	public void test2() throws IOException {
		System.out.println("test2()");
		/*1. 获取 SqlSessionFactory 对象*/
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		
		/*2.获取SqlSession对象*/
		SqlSession session = sqlSessionFactory.openSession();
		/* 获取sql映射对象 */
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		/* 执行sql */ 
		Employee e = mapper.getEmployeeId(2);
		System.out.println(mapper.getClass());
		System.out.println(e);
	}
	
	/**
	 * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
	 * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。 
	 * 3、将sql映射文件注册在全局配置文件中
	 * 4、写代码：
	 * 		1）、根据全局配置文件得到SqlSessionFactory；
	 * 		2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
	 * 			一个sqlSession就是代表和数据库的一次会话，用完关闭
	 * 		3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
	 * 
	 * @throws IOException
	 */
	/*@Test
	public void test1() throws IOException {
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		
		2、获取 SqlSession实例
//		@param sql唯一标识： statement Unique identifier matching the statement to use.
//		@param sql的参数 parameter A parameter object to pass to the statement.
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Employee emp = (Employee) session.selectOne("com.swjtu.mybatis.dao.EmployeeMapper.selectEmp", "1");
			System.out.println(emp);
		} finally {
			session.close();
		}
	}*/
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
