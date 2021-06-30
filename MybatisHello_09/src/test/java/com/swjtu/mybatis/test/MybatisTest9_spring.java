package com.swjtu.mybatis.test;

import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class MybatisTest9_spring {
	
	/**
	 * 遍历所有xml文件 
	 */
	@Test 
	public void dom4j_test2() {  
		System.out.println("dom4j_test2"); 
		ResourcePatternResolver loader = new PathMatchingResourcePatternResolver();
		try {
			Resource[] resources = loader.getResources("classpath*:com/swjtu/mybatis/**/*.mapper.xml");
			for (Resource r : resources) { 
				System.out.println(r.getFilename()); 
				Document doc = Dom4jUtil.load(r.getInputStream());
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
			}
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
}
