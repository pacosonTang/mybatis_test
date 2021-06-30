package com.swjtu.mybatis.bean;

import java.util.List;

/**
 * 封装分页查询数据 
 * @author pacoson
 *
 */
public class Page {
	
	private int start;
	private int end;
	private int count;
	private List<Employee> empList;
	
	public Page() {
	}
	
	public Page(int start, int end, int count, List<Employee> empList) {
		super();
		this.start = start;
		this.end = end;
		this.count = count;
		this.empList = empList;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
}
