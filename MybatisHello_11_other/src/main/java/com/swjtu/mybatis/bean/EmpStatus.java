package com.swjtu.mybatis.bean;

/**
 * 希望数据库保存的是
 *  100， 200， 300 状态码
 *  这就需要一个自定义的类型处理器
 */
public enum EmpStatus {
	LOGIN(100, "用户登录"), LOGOUT(200, "用户登出"), REMOVE(300, "用户不存在");
	private int code; // 状态码
	private String comment; // 注释
	
	private EmpStatus(int code, String comment) {
		this.code = code;
		this.comment = comment;
	}
	/**
	 * 根据状态码返回枚举对象
	 * @param code
	 * @return
	 */
	public static EmpStatus getEmpStatusByCode(int code) {
		switch(code) {
			case 100: return LOGIN;
			case 200: return LOGOUT;
			case 300: return REMOVE;
			default: return LOGOUT;
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
} 
