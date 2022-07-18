package com.practice.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class IncomeTaxCalculatorTest {
    @Autowired
    private IncomeTaxCalculatorService service;





    @Test
    public void incomeTaxCalculatorWhenIncomeCloseTo14000() {
        List<Income> incomes = new ArrayList<Income>();
        incomes.add(new Income(){{
            setAmount(7000.00f);
            setIncomeType("Asdad");
        }});
        incomes.add(new Income(){{
            setAmount(7000.00f);
            setIncomeType("asdasd");
        }});
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomes(incomes);

        }};

        Assert.assertEquals(1470.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }

    @Test
    public void incomeTaxCalculatorWhenIncomeBetweenZeroAnd14000() {
        List<Income> incomes = new ArrayList<Income>();
        incomes.add(new Income(){{
            setAmount(3500.00f);
            setIncomeType("ASDasad");
        }});
        incomes.add(new Income(){{
            setAmount(3500.00f);
            setIncomeType("ASsdaas");

        }});
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomes(incomes);

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
    public void incomeTaxCalculatorWhenBusinessTypeIsEmptyString() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(140000f);
            setBusinessType("");

        }};
        Assert.assertEquals(46200.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }
    @Test
    public void incomeTaxCalculatorForBusinessTypeMostCompanies() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(140000f);
            setBusinessType(BusinessTypes.MostCompanies.value);
        }};
        Assert.assertEquals(39200.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }
    @Test
    public void incomeTaxCalculatorForBusinessTypeMaoriAuthorities() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(140000f);
            setBusinessType(BusinessTypes.MaoriAuthorities.value);
        }};
        Assert.assertEquals(24500.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }

    @Test
    public void incomeTaxCalculatorForBusinessTypeNonProfit() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(140000f);
            setBusinessType(BusinessTypes.NonProfit.value);
        }};
        Assert.assertEquals(39200.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }

    @Test
    public void incomeTaxCalculatorForUnincorporatedWhenIncomeBetween70001and180000() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(140000f);
            setBusinessType(BusinessTypes.Unincorporated.value);
        }};
        Assert.assertEquals(46200.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }

    @Test
    public void incomeTaxCalculatorForTrustsWhenIncomeTypeEarned() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(150000f);
            setBusinessType(BusinessTypes.TrustsAndTrusteesEarned.value);
        }};
        Assert.assertEquals(49500.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }

    @Test
    public void incomeTaxCalculatorForTrustsWhenIncomeTypeInitial() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(150000f);
            setBusinessType(BusinessTypes.TrustsAndTrusteesInitial.value);
        }};
        Assert.assertEquals(0.00f, service.incomeTaxCalculation(income).getTaxToPay(), 2);
    }

    @Test
    public void incomeTaxCalculatorForBusinessTypeInvalid() {
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomeAmount(150000f);
            setBusinessType("Invalid");
        }};
        Exception exception = Assert.assertThrows(RuntimeException.class,()-> service.incomeTaxCalculation(income));
        Assert.assertEquals("Invalid BusinessType", exception.getMessage());
    }




    @Test
    public void incomeTaxCalculatorThrowsExceptionWhenIncomeBelowZeroFromEmptyIncomeList() {
        List<Income> incomes = new ArrayList<Income>();
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomes(incomes);
            setIncomeAmount(-7000.00f);

        }};
        Exception exception = Assert.assertThrows(RuntimeException.class,()-> service.incomeTaxCalculation(income));
        Assert.assertEquals("Income List is Empty", exception.getMessage());
    }




    @Test
    public void incomeTaxCalculatorThrowsExceptionWhenIncomeTypeExceedCharacterLimit() {
        List<Income> incomes = new ArrayList<Income>();
        incomes.add(new Income(){{
            setAmount(5000.00f);
            setIncomeType("sdsdsdadadasdasdadasdasdasddasdasddddsadasdasdasdasdretsasfdasfasdfsfadsfasdfdsafasfadsfadsfdasfdsafadsfsadfadsfdsafasdfdsvasdfadsfasfdasdfadfsdfasfadsfdsfasdfasdfdafcadsfsdafdsafsdfsfsfasdfasdfasdfsdfdsafsadfffffffffffffffffffffffdsdffdsfasdfsadfsaw");
        }});
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomes(incomes);
        }};
        Exception exception = Assert.assertThrows(RuntimeException.class,()-> service.incomeTaxCalculation(income));
        Assert.assertEquals("IncomeType Exceed Character Limit Of 250", exception.getMessage());
    }

    @Test
    public void incomeTaxCalculatorThrowsExceptionWhenIncomeTypeContainsSpecialCharacters() {
        List<Income> incomes = new ArrayList<Income>();
        incomes.add(new Income(){{
            setAmount(5000.00f);
            setIncomeType("'!<>'");
        }});
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomes(incomes);
        }};
        Exception exception = Assert.assertThrows(RuntimeException.class,()-> service.incomeTaxCalculation(income));
        Assert.assertEquals("IncomeType Contains Special Characters", exception.getMessage());
    }

    @Test
    public void incomeTaxCalculatorThrowsExceptionWhenIncomeAmountContainsNegativeValues() {
        List<Income> incomes = new ArrayList<Income>();
        incomes.add(new Income(){{
            setAmount(-5000.00f);
            setIncomeType("Valid");
        }});
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomes(incomes);
        }};
        Exception exception = Assert.assertThrows(RuntimeException.class,()-> service.incomeTaxCalculation(income));
        Assert.assertEquals("IncomeAmount Contains Negative Values", exception.getMessage());
    }

    @Test
    public void incomeTaxCalculatorThrowsExceptionWhenIncomeTypeIsEmpty() {
        List<Income> incomes = new ArrayList<Income>();
        incomes.add(new Income(){{
            setAmount(5000.00f);
            setIncomeType("");
        }});
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomes(incomes);
        }};
        Exception exception = Assert.assertThrows(RuntimeException.class,()-> service.incomeTaxCalculation(income));
        Assert.assertEquals("IncomeType is empty", exception.getMessage());
    }
    @Test
    public void incomeTaxCalculatorThrowsExceptionWhenIncomeTypeIsNull() {
        List<Income> incomes = new ArrayList<Income>();
        incomes.add(new Income(){{
            setAmount(5000.00f);
            setIncomeType(null);
        }});
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomes(incomes);
        }};
        Exception exception = Assert.assertThrows(RuntimeException.class,()-> service.incomeTaxCalculation(income));
        Assert.assertEquals("IncomeType is Null", exception.getMessage());
    }

    @Test
    public void incomeTaxCalculatorThrowsExceptionWhenIncomeAmountIsZero() {
        List<Income> incomes = new ArrayList<Income>();
        incomes.add(new Income(){{
            setAmount(0.00f);
            setIncomeType("Valid");

        }});
        CalculatorTaxRequest income = new CalculatorTaxRequest() {{
            setIncomes(incomes);
        }};
        Exception exception = Assert.assertThrows(RuntimeException.class,()-> service.incomeTaxCalculation(income));
        Assert.assertEquals("IncomeAmount cannot be zero", exception.getMessage());
    }


}
