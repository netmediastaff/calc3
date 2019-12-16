/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurice.calculator.service;

import com.futurice.calculator.model.Response;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Stack;
import org.springframework.stereotype.Service;

/**
 *
 * @author oghomwen.aigbedion
 */
@Service
public class CalculatorService {

    public Response evaluate(String expression) {
        Response response = new Response();

        String decodedExpression = "";
        try {
            decodedExpression = new String(Base64.getDecoder().decode(expression.getBytes()), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            response.setError(true);
            response.setMessage("The Character Encoding is not supported");
            return response;
        }

        try {
            char[] tokens = decodedExpression.toCharArray();

            // Stack for numbers: 'values' 
            Stack<Double> values = new Stack<>();

            // Stack for Operators: 'ops' 
            Stack<Character> ops = new Stack<>();

            for (int i = 0; i < tokens.length; i++) {
                // Current token is a whitespace, skip it 
                if (tokens[i] == ' ') {
                    continue;
                }

                // Current token is a number, push it to stack for numbers 
                if (tokens[i] >= '0' && tokens[i] <= '9') {
                    StringBuilder sbuf = new StringBuilder();
                    // There may be more than one digits in number 
                    while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
                        sbuf.append(tokens[i++]);
                    }
                    i--;

                    values.push(Double.parseDouble(sbuf.toString()));
                } // Current token is an opening brace, push it to 'ops' 
                else if (tokens[i] == '(') {
                    ops.push(tokens[i]);
                } // Closing brace encountered, solve entire brace 
                else if (tokens[i] == ')') {
                    while (ops.peek() != '(') {
                        values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                    }
                    ops.pop();
                } // Current token is an operator. 
                else if (tokens[i] == '+' || tokens[i] == '-'
                        || tokens[i] == '*' || tokens[i] == '/') {
                    // While top of 'ops' has same or greater precedence to current 
                    // token, which is an operator. Apply operator on top of 'ops' 
                    // to top two elements in values stack 
                    while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                        values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                    }

                    // Push current token to 'ops'. 
                    ops.push(tokens[i]);
                } else {
                    response.setError(true);
                    response.setMessage("Unsupported operation found : " + tokens[i]);
                    return response;
                }

            }

            // Entire expression has been parsed at this point, apply remaining 
            // ops to remaining values 
            while (!ops.empty()) {
                values.push(applyOp(ops.pop(), values.pop(), values.pop()));
            }

            // Top of 'values' contains result, return it 
            response.setError(false);
            response.setResult(values.pop());
        } catch (Exception e) {
            response.setError(true);
            response.setMessage("Invalid expression");
        }
        return response;
    }

    // Returns true if 'op2' has higher or same precedence as 'op1', 
    // otherwise returns false. 
    public boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        } else {
            return true;
        }
    }

    // A utility method to apply an operator 'op' on operands 'a'  
    // and 'b'. Return the result. 
    public double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }

}
