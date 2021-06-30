package com.swjtu.mybatis.util;

import java.io.File;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class Dom4jUtil {
	
	public static Document load(String fileName) {
		Document doc = null;
		try {
			SAXReader reader = new SAXReader();
			doc = reader.read(new File(fileName));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public static Document load(InputStream input) {
		Document doc = null;
		try {
			SAXReader reader = new SAXReader();
			doc = reader.read(input);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
}
