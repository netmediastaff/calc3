/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurice.calculator.controller;

import com.futurice.calculator.model.Response;
import com.futurice.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author oghomwen.aigbedion
 */
@RestController
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @RequestMapping(value = "calculus", method = RequestMethod.GET)
    public Response doCalculation(@RequestParam("query") String expression) {
        return calculatorService.evaluate(expression);
    }
}
