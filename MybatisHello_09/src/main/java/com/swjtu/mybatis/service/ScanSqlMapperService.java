package com.swjtu.mybatis.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.swjtu.mybatis.bean.TableBean;
import com.swjtu.mybatis.util.Dom4jUtil;
import com.swjtu.mybatis.util.JdbcUtil;
import com.swjtu.mybatis.util.MapperReader;

public class ScanSqlMapperService {
	private static Log log = LogFactory.getLog(ScanSqlMapperService.class);
	
	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<>();
		ScanSqlMapperService service = new ScanSqlMapperService();
		service.main(params); 
	}
	/**
	 * 主方法  
	 * @param params
	 */
	public void main(Map<String, Object> params) {
		/* 获取数据库连接 */
		Connection conn = JdbcUtil.getConnection();
		/* 获取数据库表和索引 */
		Map<String, TableBean> map = this.getDbTableAndIndexes(conn, "jdbc_test");
		for (TableBean bean:map.values()) {
			System.out.println(bean);
		}
		/* 获取所有的数据库表 */
		Set<String> tableNames = map.keySet();
		/* 解析 mapper 文件 */ 
		String pattern = "classpath*:com/swjtu/mybatis/**/*.mapper.xml"; 
		this.parseSqlXml(pattern);
	}
	/**
	 * 解析sql xml文件 
	 * @param pattern
	 */ 
	private void parseSqlXml(String pattern) {
		Resource[] resources = MapperReader.readResource(pattern);
		try {
			/* 遍历mapper文件并解析sql */
			for (Resource r : resources) { 
				System.out.println(r.getFilename()); 
				Document doc = Dom4jUtil.load(r.getInputStream());
				/* 获取根节点 */ 
				Element root = doc.getRootElement();
				;
				System.out.println(root.attributeValue("namespace"));
				/* 遍历  */ 
				for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
					Element e = it.next();
					String sqlBody = e.getText().toLowerCase();
					sqlBody.contains("from"); 
					/** 
						SELECT id AS ID 
						       , last_name AS LASTNAME
						       , email AS EMAIL 
						       , gender AS GENDER
						  FROM emp_tbl 
						 WHERE id = #{ID}
					 */
					System.out.println(e.attribute("id").getText());  // getEmployeeId 
				} 
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	/** 
	 * 获取数据库表与索引 
	 * @param conn
	 * @param dbName
	 * @return
	 */
	public Map<String, TableBean> getDbTableAndIndexes(Connection conn, String dbName) {
		List<TableBean> tableBeanList = new ArrayList<>();
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			/* 获取数据库 jdbc_test的数据库表 */
			ResultSet rs = metaData.getTables(dbName, null, "%", new String[]{"TABLE"});
			while (rs.next()) {
				/* 获取表明 */
				String tableName = rs.getString("TABLE_NAME");
				/* 获取表索引 */
				ResultSet indexRs = metaData.getIndexInfo(dbName, null, tableName, false, true);
				Set<String> indexSet = new HashSet<>();
				while (indexRs.next()) {
					indexSet.add(indexRs.getString("COLUMN_NAME"));
				}
				/* 添加到表对象列表 */
				tableBeanList.add(new TableBean(tableName, indexSet));
			}  
		} catch (Exception e) { 
			e.printStackTrace();
		} 
		/* 把list转为 map */
		Map<String, TableBean> map = new HashMap<>();
		for (TableBean bean : tableBeanList) {
			map.put(bean.getTableName(), bean);
		}
		return map; 
	}
	
	/**
	 * 根据模式获取mapper文件资源 
	 * @param pattern
	 * @return
	 */
	private Resource[] readResource(String pattern) {
//		pattern = "classpath*:com/swjtu/mybatis/**/*.mapper.xml";
		ResourcePatternResolver loader = new PathMatchingResourcePatternResolver();
		try {
			return loader.getResources(pattern);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null; 
	}
}
