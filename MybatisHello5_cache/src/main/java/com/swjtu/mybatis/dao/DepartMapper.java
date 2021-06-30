package com.swjtu.mybatis.dao;

import com.swjtu.mybatis.bean.Department;

/*接口*/
public interface DepartMapper {
	
	/* 添加一个部门 */
	public int addDept(String deptName);
	
	/* 基于 collection 标签分步查询部门及其客户列表 */
	public Department getDeptByIdByStep(Integer id);
	
	/* 查询部门及其员工列表 */
	public Department getDeptAndEmpsById(Integer id);
	
	/* 通过id查询部门 */
	public Department getDeptById(Integer id);
}
