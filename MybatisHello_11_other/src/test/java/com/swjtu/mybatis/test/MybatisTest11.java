package com.swjtu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swjtu.mybatis.bean.EmpStatus;
import com.swjtu.mybatis.bean.Employee;
import com.swjtu.mybatis.bean.MaxKey;
import com.swjtu.mybatis.dao.EmployeeMapper;

public class MybatisTest11 {

	
	/**
	 * 查询枚举属性并封装
	 * Employee类的status属性就是 枚举属性
	 * @throws IOException
	 */
	@Test
	public void testEnumSelect() throws IOException {
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try { 
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			Employee e = mapper.getEmployeeId(20058);
			System.out.println("status = " + e.getStatus());
			// 打印结果： status = LOGOUT
		} finally { 
			session.close();
		}
	}
	/**
	 * mybatis 如何处理枚举类型
	 * mybaits在处理枚举对象的时候，默认保存的是枚举的名字；TypeHandler， EnumTypeHandler
	 * 改变：使用另外一种枚举类型处理器 EnumOrdinalTypeHandler, 保存的是枚举的ordinal（序号，从0开始）
	 * @throws IOException
	 */
	@Test
	public void testEnumInsert() throws IOException {
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try { 
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			int insertRows = mapper.addEmp(new Employee(null, "enum_name4", "enum_email4", "f", "1940"));
			System.out.println("插入的记录数为 " + insertRows);
			session.commit();
		} finally { 
			session.close();
		}
	}
	
	/**
	 * 测试枚举类如何使用
	 * @throws IOException
	 */
	@Test
	public void testEnumUse() throws IOException {
		EmpStatus login = EmpStatus.LOGIN;
		EmpStatus logout = EmpStatus.LOGOUT;
		
		System.out.println("ordinal = " + login.ordinal());
		System.out.println("name = " + login.name());
		System.out.println("ordinal = " + logout.ordinal());
		System.out.println("name = " + logout.name());
		
		System.out.println("枚举状态码：" + login.getCode());
		System.out.println("枚举提示消息：" + login.getComment());
	}
	
	
	/**
	 * 测试存储过程：分页
	 * @throws IOException
	 */
	@Test
	public void testProcedure() throws IOException {
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try { 
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			MaxKey key = mapper.getMaxKey();
			System.out.println("调用存储过程统计 emp_tbl表主键最大值： max value = " + key.getMaxKey());
			
			session.commit();
		} finally { 
			session.close();
		}
	}
	
	/**
	 * 批量插入员工
	 * @throws IOException
	 */
	@Test
	public void testBatch() throws IOException {
		/*获取 sqlSessionFactory 对象*/
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		/*获取可以批量执行的 SqlSession 对象*/
//		SqlSession session = sqlSessionFactory.openSession();
		SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
		long start = System.currentTimeMillis();
		try { 
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			int startIndex = 30000;
			for (int i = startIndex; i < startIndex + 10000; i++) { 
				String gender = i % 2 == 0 ? "f":"m";
				String deptId =String.valueOf(i % 2 == 0 ? 2 : 1);
				mapper.addEmp(new Employee(null, "dayuan"+i, i+"@qq.com", gender, deptId));
			}
			session.commit();
			long end = System.currentTimeMillis();
			System.out.println("执行时长 = " + (end - start));
		} finally { 
			session.close();
		}
	}
	
	
	/**
	 * 使用PageInfo封装查询的员工列表
	 * @throws IOException
	 */
	@Test
	public void getEmpListByPageInfo() throws IOException {
		/*获取 sqlSessionFactory 对象*/
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		/*获取 SqlSession 对象*/
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			// 分页查询
			Page<Object> page = PageHelper.startPage(2, 10);
			List<Employee> empList = mapper.getEmpList(1);
			int i = 0;
			for (Employee e : empList) {
				System.out.println("row " + ++i +" ==> "+ e);
			}
			
			/*使用PageInfo封装查询的员工列表*/
			// 传入连续显示多少页， 如连续显示2页 
			PageInfo<Employee> pageInfo = new PageInfo<>(empList, 2);
			//测试PageInfo全部属性
			//PageInfo包含了非常全面的分页属性
			System.out.println("当前页码 = " + pageInfo.getPageNum());
			System.out.println("总记录数目 = " + pageInfo.getTotal());
			System.out.println("每页记录数目 = " + pageInfo.getPageSize());
			System.out.println("总页码 = " + pageInfo.getPages());
			System.out.println("是否第一页：" + pageInfo.isIsFirstPage());
			System.out.println("连续显示的页码：");
			int[] nums = pageInfo.getNavigatepageNums();
			for (i = 0; i < nums.length; i++) {
				System.out.println(nums[i]);
			}
		} finally { 
			session.close();
		}
	}
	
	/**
	 * 查询员工列表
	 * @throws IOException
	 */
	@Test
	public void getEmpList() throws IOException {
		/*获取 sqlSessionFactory 对象*/
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		/*获取 SqlSession 对象*/
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			// 分页查询
			Page<Object> page = PageHelper.startPage(2, 10);
			List<Employee> empList = mapper.getEmpList(1);
			
			int i = 0;
			for (Employee e : empList) {
				System.out.println("row " + ++i +" ==> "+ e);
			}
			
			System.out.println("当前页码 = " + page.getPageNum());
			System.out.println("总记录数目 = " + page.getTotal());
			System.out.println("每页记录数目 = " + page.getPageSize());
			System.out.println("总页码 = " + page.getPages());
		} finally { 
			session.close();
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
