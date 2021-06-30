package com.swjtu.mybatis.bean;

import org.apache.ibatis.type.Alias;

@Alias("MaxKey")
public class MaxKey {
	int maxKey;

	public MaxKey() {
	}

	public MaxKey(int maxKey) {
		this.maxKey = maxKey;
	}

	public int getMaxKey() {
		return maxKey;
	}

	public void setMaxKey(int maxKey) {
		this.maxKey = maxKey;
	}
	
	
}
