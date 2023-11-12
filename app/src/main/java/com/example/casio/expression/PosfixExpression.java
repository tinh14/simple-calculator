package com.example.casio.expression;

import java.util.List;
import java.util.Stack;

import com.example.casio.constants.Constants;

// Biểu thức hậu tố
public class PosfixExpression {
	
	// Tính biểu thức
	public static double evaluate(List<String> posfix) throws ArithmeticException, Exception{	
		
		Stack<Double> stack = new Stack<>();
		
		for (String str : posfix) {
		
			if (ExpressionUtils.isOperand(str)) {
				double val = Double.valueOf(str);
				stack.push(val);
			}else if (MathFunctions.isFunc(str)) {
				
				double val1 = stack.pop();
				
				if (MathFunctions.isTwoParamFunc(str)) {
					double val2 = stack.pop();
					stack.push(MathFunctions.execute(str, val2, val1));
				}else {
					stack.push(MathFunctions.execute(str, val1));
				}

			// Nếu là hằng số
			}else if (Constants.isConstant(str)){
				stack.push(Constants.getConstant(str));
			// Nếu là toán tử
			}else if (ExpressionUtils.isOperator(str)){
				double val1 = stack.pop();
				double val2 = stack.pop();
				// Áp dụng toán tử vào 2 toán hạng
				stack.push(ExpressionUtils.applyOperator(str, val2, val1));				
			}
		}

		Double val = stack.pop();

		if (Double.isInfinite(val)){
			throw new ArithmeticException();
		}

		// Làm tròn 5 chữ số cuối sau dấu phẩy động và trả về kết quả
		return Math.round(val * 100000.0) / 100000.0;
	}
}
