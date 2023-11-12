package com.example.casio.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	
	// Hằng số pi
	public static final String PI = "π";
	
	// Hằng số euler
	public static final String E = "e";
	
	// Bảng ánh xạ có key là ký tự hiển thị của hằng số và value là giá 
	// trị thật của nó
	private static final Map<String, Double> constants = new HashMap<String, Double>();

	static {
		constants.put(PI, Math.PI);
		constants.put(E, Math.E);
	}
	
	// Kiểm tra hằng số
	public static boolean isConstant(String str) {
		return constants.containsKey(str);
	}
	
	// Lấy giá trị của hằng số
	public static Double getConstant(String str) {
		return constants.get(str);
	}
}
