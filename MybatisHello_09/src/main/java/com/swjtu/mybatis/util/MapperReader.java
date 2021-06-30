package com.swjtu.mybatis.util;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class MapperReader {
	
	public static Resource[] readResource(String pattern) {
//		pattern = "classpath*:com/swjtu/mybatis/**/*.mapper.xml";
		ResourcePatternResolver loader = new PathMatchingResourcePatternResolver(MapperReader.class.getClassLoader());
		try {
			return loader.getResources(pattern);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null; 
	}
}
