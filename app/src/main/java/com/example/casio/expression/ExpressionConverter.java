package com.example.casio.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.example.casio.constants.Constants;

// Chuyển đổi biểu thức
public class ExpressionConverter {

	private static boolean isValidInfix(List<String> infix){
		Stack<String> stack = new Stack<>();

		for (String str : infix) {
			if (ExpressionUtils.isOpenParenthesis(str) || MathFunctions.isFunc(str)) {
				stack.push("(");
			}else if (ExpressionUtils.isCloseParenthesis(str)){
				if (stack.isEmpty()) {
					return false;
				}
				stack.pop();
			}
		}

		return stack.isEmpty();
	}
	
	// Chuyển biểu thức trung tố sang hậu tố
	public static List<String> infixToPosfix(List<String> infix) throws Exception {
		if (!isValidInfix(infix)) {
			throw new Exception();
		}
		
		List<String> posfix = new ArrayList<>();
		
		Stack<String> stack = new Stack<>();
		
		for (String str : infix) {
			if (ExpressionUtils.isOperand(str) || Constants.isConstant(str)) {
				posfix.add(str);
			}else if (ExpressionUtils.isOpenParenthesis(str) || MathFunctions.isFunc(str)) {
				stack.push(str);
			}else if (ExpressionUtils.isCloseParenthesis(str)) {
				// Đọc các toán tử trong stack cho đến khi gặp dấu '(' hoặc hàm số
				while (!stack.isEmpty() && (!ExpressionUtils.isOpenParenthesis(stack.peek()) 
						&& !MathFunctions.isFunc(stack.peek()))) {
					posfix.add(stack.pop());
				}
				// Xóa '(' còn lại
				String remain = stack.pop();
				
				// Nếu chuỗi còn lại là hàm số thay vì '(' thì add vào posfix
				// Ví dụ "Sin(" thì add lại vào posfix...
				if (MathFunctions.isFunc(remain)) {
					posfix.add(remain);
				}
			}else if(ExpressionUtils.isOperator(str)) {
				// So sánh độ ưu tiên toán tử hiện tại và toán tử trong stack
				// Nếu toán tử hiện tại có độ ưu tiên <= toán tử trong stack
				// thì lấy ra và nối vào chuỗi posfix
				while (!stack.isEmpty() && ExpressionUtils.getPrecedence(str) <= ExpressionUtils.getPrecedence(stack.peek())) {
					posfix.add(stack.pop());
				}
				stack.push(str);
			}
		}
		
		// Khi đã duyệt ra hết chuỗi thì nối các toán tử còn lại vào chuỗi posfix
		while (!stack.isEmpty()) {
			posfix.add(stack.pop());
		}
		
		return posfix;
	}
}
