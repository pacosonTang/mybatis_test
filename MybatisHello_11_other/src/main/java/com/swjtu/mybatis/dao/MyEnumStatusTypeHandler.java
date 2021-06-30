package com.swjtu.mybatis.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.swjtu.mybatis.bean.EmpStatus;

/**
 * 实现自定义类型处理器的步骤
 * 1、实现 TypeHandler 或 继承 BaseTypeHandler
 * 2、在 mybatis全局配置文件中注册该 处理器
 */
public class MyEnumStatusTypeHandler implements TypeHandler<EmpStatus>{

	/**
	 * 定义当前数据如何保存到数据库中
	 */
	@Override
	public void setParameter(PreparedStatement ps, int i, EmpStatus parameter,
			JdbcType jdbcType) throws SQLException {
		ps.setString(i, String.valueOf(parameter.getCode()));
	}
	
	/**
	 * 根据数据库中获得的状态码返回 EmpStatus对象
	 */
	@Override
	public EmpStatus getResult(ResultSet rs, String columnName)
			throws SQLException {
		// 根据数据库中获得的状态码返回 EmpStatus对象
		int code = rs.getInt(columnName);
		System.out.println("从数据库中获取的状态码：" + code);
		return EmpStatus.getEmpStatusByCode(code);
	}

	@Override
	public EmpStatus getResult(ResultSet rs, int columnIndex)
			throws SQLException {
		int code = rs.getInt(columnIndex);
		System.out.println("从数据库中获取的状态码：" + code);
		return EmpStatus.getEmpStatusByCode(code);
	}

	/**
	 * CallableStatement 表示存储过程的获取方法
	 */
	@Override
	public EmpStatus getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		int code = cs.getInt(columnIndex);
		System.out.println("从数据库中获取的状态码：" + code);
		return EmpStatus.getEmpStatusByCode(code);
	}
}
