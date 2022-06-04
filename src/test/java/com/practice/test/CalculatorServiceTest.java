package com.practice.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorServiceTest {
    @Autowired
    private CalculatorService service;

    @Test
    public void divisionReturnZeroWhenDivisorValueIsNegative(){
        int value1 = 1;
        int value2 = -1;
        Assert.assertEquals(0, service.division(value1, value2));
    }

    @Test
    public void divisionReturnZeroWhenDivisorValueIsZero(){
        int value1 = 1;
        int value2 = 0;
        Assert.assertEquals(0, service.division(value1, value2));
    }

    @Test
    public void divisionReturnZeroWhenDividendValueIsNegative(){
        int value1 = -1;
        int value2 = 1;
        Assert.assertEquals(0, service.division(value1, value2));
    }

    @Test
    public void divisionReturnZeroWhenDivisorAndDividendValueAreNegative(){
        int value1 = -1;
        int value2 = -1;
        Assert.assertEquals(0, service.division(value1, value2));
    }

    @Test
    public void divisionReturnPositiveWhenDivisorAndDividendValueArePositive(){
        int value1 = 1;
        int value2 = 1;
        Assert.assertEquals(1, service.division(value1, value2));
    }

    @Test
    public void multiplyOperation(){

    }
}
