package com.example.casio.expression;

import java.util.HashMap;
import java.util.Map;

public class MathFunctions {
	
	private interface Function{
		Double execute(double...values);
	}
	
	public static final String SIN = "Sin(";
	public static final String COS = "Cos(";
	public static final String TAN = "Tan(";
	public static final String COT = "Cot(";
	
	public static final String POW = "Pow(";
	public static final String SQRT = "Sqrt(";
	public static final String LN = "Ln(";
	public static final String LOG = "Log(";
	

	private static Map<String, Function> oneParamFuncs = new HashMap<String, Function>();
	private static Map<String, Function> twoParamFuncs = new HashMap<String, Function>();
	
	
	static {
		oneParamFuncs.put(SIN, (x) -> Math.sin(x[0]));
		oneParamFuncs.put(COS, (x) -> Math.cos(x[0]));
		oneParamFuncs.put(TAN, (x) -> Math.tan(x[0]));
		oneParamFuncs.put(COT, (x) -> 1.0 / Math.tan(x[0]));
		oneParamFuncs.put(SQRT, (x) -> Math.sqrt(x[0]));
		oneParamFuncs.put(LN, (x) -> Math.log(x[0]));
		
		twoParamFuncs.put(POW, (x) -> Math.pow(x[0], x[1]));
		twoParamFuncs.put(LOG, (x) -> Math.log(x[1]) / Math.log(x[0]));
	}
	
	public static boolean isFunc(String str) {
		return isOneParamFunc(str) || isTwoParamFunc(str);
	}
	
	public static boolean isOneParamFunc(String str) {
		return oneParamFuncs.containsKey(str);
	}
	
	public static boolean isTwoParamFunc(String str) {
		return twoParamFuncs.containsKey(str);
	}
	
	public static Double execute(String str, double...values) {
		Function func = oneParamFuncs.get(str);
		if (isTwoParamFunc(str)) {
			func = twoParamFuncs.get(str);
		}
		return func.execute(values);
	}
}
