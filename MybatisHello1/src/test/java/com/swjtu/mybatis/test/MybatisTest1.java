package com.swjtu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.swjtu.mybatis.bean.Employee;
import com.swjtu.mybatis.dao.EmployeeMapper;

public class MybatisTest1 {
	
	/**
	 * 根据接口来获取sql映射
	 * @throws IOException
	 */
	@Test
	public void test2() throws IOException {
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
	@Test
	public void test1() throws IOException {
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
		
		/*2、获取 SqlSession实例*/
//		@param sql唯一标识： statement Unique identifier matching the statement to use.
//		@param sql的参数 parameter A parameter object to pass to the statement.
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Employee emp = (Employee) session.selectOne("com.swjtu.mybatis.dao.emp.qryEmpByRcrdId", "1");
			System.out.println(emp);
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
