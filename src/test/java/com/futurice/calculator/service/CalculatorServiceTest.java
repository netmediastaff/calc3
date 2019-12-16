/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurice.calculator.service;

import com.futurice.calculator.model.Response;
import static org.junit.Assert.*;

/**
 *
 * @author oghomwen.aigbedion
 */
public class CalculatorServiceTest {

    public CalculatorServiceTest() {
    }
    
    CalculatorService calculatorService = new CalculatorService();

    @org.junit.Test
    public void testEvaluate() {
        System.out.println("evaluate");
        String expression = "MiAqICgyMy8oMTApKQ==";
        Response expResult = new Response(4.6);
        Response result = calculatorService.evaluate(expression);
        assertEquals(expResult.getResult(), result.getResult(), 0.0);
    }

    @org.junit.Test
    public void testHasPrecedence() {
        System.out.println("hasPrecedence");
        char op1 = '+';
        char op2 = '*';
        boolean expResult = true;
        boolean result = calculatorService.hasPrecedence(op1, op2);
        assertEquals(expResult, result);
    }

    @org.junit.Test
    public void testApplyOp() {
        System.out.println("applyOp");
        char op = '/';
        double b = 2.0;
        double a = 4.0;
        double expResult = 2.0;
        double result = calculatorService.applyOp(op, b, a);
        assertEquals(expResult, result, 0.0);
    }

}
