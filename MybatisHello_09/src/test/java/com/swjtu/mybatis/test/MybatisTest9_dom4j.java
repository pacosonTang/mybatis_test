package com.swjtu.mybatis.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class MybatisTest9_dom4j {
	
	/**
	 * 查询sql 
	 */
	@Test 
	public void dom4j_test1() {  
		String resource = "mybatis-config.xml";
		String path = System.getProperty("user.dir") + File.separator 
				+ "src" + File.separator + "main" + File.separator 
				+"java" + File.separator + "com" + File.separator + 
				"swjtu" + File.separator + "mybatis" + File.separator 
				+ "dao" + File.separator + "employee.mapper.xml";
		try { 
			InputStream inputStream = new FileInputStream(new File(path));
			Document doc = Dom4jUtil.load(inputStream);
			/* 获取根节点 */
			Element root = doc.getRootElement();
			/* 遍历  */
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element e = it.next();
				System.out.println(e.getText()); 
				/**
					SELECT id AS ID 
					       , last_name AS LASTNAME
					       , email AS EMAIL 
					       , gender AS GENDER
					  FROM emp_tbl 
					 WHERE id = #{ID}
				 */
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 遍历所有xml文件 
	 */
	@Test 
	public void dom4j_test2() {  
		String resource = "mybatis-config.xml";
		ResourcePatternResolver loader = new PathMatchingResourcePatternResolver();
		try {
			Resource[] resources = loader.getResources("classpath*:com/swjtu/mybatis/**/*.mapper.xml");
			for (Resource r : resources) { 
				System.out.println(r.getFile().getCanonicalPath());
				System.out.println(r.getFile().getPath());
				System.out.println(r.getFilename()); 
				Document doc = Dom4jUtil.load(r.getInputStream());
				/* 获取根节点 */
				Element root = doc.getRootElement();
				/* 遍历  */
				for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
					Element e = it.next();
					System.out.println(e.attributeValue("id"));
					System.out.println(e.getText()); 
					/**
						SELECT id AS ID 
						       , last_name AS LASTNAME
						       , email AS EMAIL 
						       , gender AS GENDER
						  FROM emp_tbl 
						 WHERE id = #{ID}
					 */
				} 
			}
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
}
