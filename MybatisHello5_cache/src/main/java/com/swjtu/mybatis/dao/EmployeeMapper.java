package com.swjtu.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.swjtu.mybatis.bean.Employee;

/*接口*/
public interface EmployeeMapper {
	// 多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
	//@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
	@MapKey("LASTNAME") 
	public Map<String, Employee> getEmpByLastNameLikeReturnMap(String lastName);
	
	// 把查询结果封装到List<Map>
	public List<Map<String, Object>> getEmpListByLastNameReturnListMap(String lastName);
	
	public Map<String, Object> getEmpByIdReturnMap(int id);
	
	public List<Employee> getEmpListByLastName(String lastName);
	
	public Employee getEmpByMap(Map<String, Object> params);
	
	/*查询employee id*/
	public Employee getEmployeeId(Integer id) ;
	/*添加一个员工*/
	public int addEmp(Employee e);
	/*更新一个员工*/
	public int updateEmp(Employee e);
	/*删除一个员工*/
	public int deleteEmp(Integer id);
	
	/* 根据id和lastName查询员工 */
	public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);
}
