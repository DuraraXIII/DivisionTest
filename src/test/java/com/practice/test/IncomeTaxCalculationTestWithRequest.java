package com.practice.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IncomeTaxCalculationTestWithRequest {

    @Autowired
    private IncomeTaxCalculatorService service;


    @Test
    public void incomeTaxCalculatorWhenIncomeBelowZero() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(-7000f);
        }};
        Assert.assertEquals(-735.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }

    @Test
    public void incomeTaxCalculatorWhenIncomeIsZero() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(0f);
        }};
        Assert.assertEquals(0.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }

    @Test
    public void incomeTaxCalculatorWhenIncomeCloseTo14000() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(14000f);
        }};
        Assert.assertEquals(1470.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }

    @Test
    public void incomeTaxCalculatorWhenIncomeBetweenZeroAnd14000() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(7000f);
        }};
        Assert.assertEquals(735.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }

    @Test
    public void incomeTaxCalculatorWhenIncomeBetween14001And48000() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(14001f);
        }};
        Assert.assertEquals(2450.17f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }


    @Test
    public void incomeTaxCalculatorWhenIncomeBetween48001and70000() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(70000f);
        }};
        Assert.assertEquals(21000.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }

    @Test
    public void incomeTaxCalculatorWhenIncomeBetween70001and180000() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(140000f);
        }};
        Assert.assertEquals(46200.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }

    @Test
    public void incomeTaxCalculatorForSoleTraderWhenIncomeBetween70001and180000() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(140000f);
            setBusinessType(BusinessTypes.SoleTrader.value);
        }};
        Assert.assertEquals(46200.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }

    @Test
    public void incomeTaxCalculatorWhenIncomeTypeIsEmptyString() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(140000f);
            setBusinessType("");
        }};
        Assert.assertEquals(46200.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }


}

