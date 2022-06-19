package com.practice.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IncomeTaxCalculationTest {

    @Autowired
    private IncomeTaxCalculatorService service;



    @Test
    public void incomeTaxCalculatorWhenIncomeBelowZero(){
        float income = -7000f;
        Assert.assertEquals(-735.00f, service.incomeTaxCalculation(income), 2);
    }

    @Test
    public void incomeTaxCalculatorWhenIncomeIsZero(){
        float income = 0f;
        Assert.assertEquals(0.00f, service.incomeTaxCalculation(income), 2);
    }

    @Test
    public void incomeTaxCalculatorWhenIncomeCloseTo14000(){
        float income = 14000f;
        Assert.assertEquals(1470.00f, service.incomeTaxCalculation(income), 2);
    }

    @Test
    public void incomeTaxCalculatorWhenIncomeBetweenZeroAnd14000(){
        float income = 7000f;
        Assert.assertEquals(735.00f, service.incomeTaxCalculation(income), 2);
    }

    @Test
    public void incomeTaxCalculatorWhenIncomeBetween14001And48000(){
        float income = 14001f;
        Assert.assertEquals(2450.17f, service.incomeTaxCalculation(income), 2);
    }


    @Test
    public void incomeTaxCalculatorWhenIncomeBetween48001and70000(){
        float income = 70000f;
        Assert.assertEquals(21000.00f, service.incomeTaxCalculation(income), 2);
    }

    @Test
    public void incomeTaxCalculatorWhenIncomeBetween70001and180000(){
        float income = 140000f;
        Assert.assertEquals(46200.00f, service.incomeTaxCalculation(income), 2);
    }

    @Test
    public void incomeTaxCalculatorWhenIncomeOver180000(){
        float income = 190000f;
        Assert.assertEquals(74100.00f, service.incomeTaxCalculation(income), 2);
    }



}
