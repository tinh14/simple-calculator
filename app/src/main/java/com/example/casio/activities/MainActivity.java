package com.example.casio.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.casio.R;
import com.example.casio.expression.ExpressionConverter;
import com.example.casio.expression.ExpressionUtils;
import com.example.casio.expression.PosfixExpression;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridLayout parentLayout;
    TextView textView;
    Button buttonClear, buttonAllClear, buttonResult;
    List<String> infix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infix = new ArrayList<>();
        parentLayout = findViewById(R.id.parent);
        textView = findViewById(R.id.textViewResult);

        for (int i = 0; i < parentLayout.getChildCount(); i++) {
            View view = parentLayout.getChildAt(i);

            if (view instanceof Button) {
                final Button button = (Button) view;
                button.setOnClickListener(v -> {
                    String buttonText = button.getText().toString();
                    String currentText = textView.getText().toString();

                    if(infix.isEmpty()){
                        infix.add(buttonText);
                        textView.setText(currentText + buttonText);
//                        textView.setText(infix.toString());
                        return;
                    }
                    int lastIndex = infix.size() - 1;
                    String prev = infix.get(lastIndex);

                    if((ExpressionUtils.isOperand(buttonText) || ExpressionUtils.isFloatingPoint(buttonText)) && ExpressionUtils.isOperand(prev)){
                        infix.set(lastIndex,prev + buttonText);
                    } else {
                        infix.add(buttonText);
                    }
                    textView.setText(currentText + buttonText);
//                    textView.setText(infix.toString());
                });
            }
        }

        buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(v -> {
            if(infix.isEmpty()) {
                return;
            }
            int lastIndex = infix.size() - 1;
            String lastElement = infix.get(lastIndex);
            if (ExpressionUtils.isOperand(lastElement) && lastElement.length() > 1){
                lastElement = lastElement.substring(0, lastElement.length() - 1);
                infix.set(lastIndex, lastElement);
            }else {
                infix.remove(lastIndex);
            }
            String currentText = textView.getText().toString();
            textView.setText(currentText.substring(0,currentText.length()-1));
//            textView.setText(infix.toString());
        });

        buttonAllClear = findViewById(R.id.buttonAllClear);
        buttonAllClear.setOnClickListener(v -> {
            textView.setText("");
            infix.clear();
        });

        buttonResult = findViewById(R.id.buttonResult);
        buttonResult.setOnClickListener(v -> {
            try {
                List<String> posfix = ExpressionConverter.infixToPosfix(infix);
//                textView.setText(infix.toString());
//
//                textView.setText(posfix.toString());

                Double val = PosfixExpression.evaluate(posfix);
                textView.setText(val.toString());

            }catch (ArithmeticException e) {
                textView.setText("Cannot divide by zero");
            }catch (Exception e) {
                textView.setText("Invalid expression");
            }
        });

    }
}