package com.swjtu.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.swjtu.mybatis.bean.Employee;


/*接口*/
public interface EmployeeMapperDynamicSQL {
	
	/*使用  sql标签 插入员工列表 */
	public int addEmpListByForeachAndSql(List<Employee> list);
	
	/*使用  sql标签 查询员工列表 */
	public List<Employee> getEmpListBySql(@Param("LAST_NAME") String lastName);
	
	/*使用  bind标签 查询员工列表  */
	public List<Employee> getEmpListByBind(@Param("LAST_NAME") String lastName);
	
	/*使用  mybatis内置参数 查询员工列表 */
	public List<Employee> getEmpListByInnerParameter(List<Object> list);
	
	/*使用foreach标签插入员工列表 */
	public int addEmpListByForeach(List<Employee> list);
	
	/*使用foreach标签查询员工列表 */
	public List<Employee> getEmpListByConditionForeach(List<Integer> list);
	
	/* 使用set标签更新员工 */
	public int updEmp(Map<String, Object> map);
	
	/*带了哪个字段，查询条件就带上这个字段的值 */
	public List<Employee> getEmpListByConditionChoose(Map<String, Object> map);
	
	/*带了哪个字段，查询条件就带上这个字段的值 */
	public List<Employee> getEmpListByConditionIf(Map<String, Object> map);
	
	/*带了哪个字段，查询条件就带上这个字段的值 */
	public List<Employee> getEmpListByConditionTrim(Map<String, Object> map);
}
