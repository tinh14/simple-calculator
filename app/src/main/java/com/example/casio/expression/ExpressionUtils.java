package com.example.casio.expression;

// Lớp tiện ích dành cho biểu thức
public class ExpressionUtils {
	
	// Kiểm tra toán hạng
	public static boolean isOperand(String str) {
		Double val = null;
		try {
			val = Double.valueOf(str);
		}catch (Exception e) {
			
		}
		return val != null;
	}
	
	// Kiểm tra toán tử
	public static boolean isOperator(String str) {
		String[] ops = {"+", "-", "*", "/", "mod"};
		for (int i = 0; i < ops.length; i++) {
			if (str.equals(ops[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFloatingPoint(String str) {
		return str.equals(".");
	}
	
	// Kiểm tra ngoặc tròn mở
	public static boolean isOpenParenthesis(String str) {
		return str.equals("(");
	}
	
	// Kiểm tra ngoặc tròn đóng
	public static boolean isCloseParenthesis(String str) {
		return str.equals(")");
	}

	// Lấy độ ưu tiên toán tử
	public static int getPrecedence(String str) {
		switch (str) {
			case "+":
			case "-":
				return 1;
			case "*":
			case "/":
			case "mod":
				return 2;
		}
		return -1;
	}

	// Nối chuỗi vào biểu thức
	public static String getNewExpression(String exp, String str) {
		return exp += str;
	}
	
	// Áp dụng toán tử
	public static double applyOperator(String op, double val1, double val2) throws ArithmeticException{
		switch (op) {
			case "+":
				return val1 + val2;
			case "-":
				return val1 - val2;
			case "*":
				return val1 * val2;
			case "/":
				return val1 / val2;
			default:
				return val1 % val2;
		}
	}

}
