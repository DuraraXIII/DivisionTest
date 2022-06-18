package com.practice.test;

import org.springframework.stereotype.Service;

@Service
public class IncomeTaxCalculatorServiceImp implements IncomeTaxCalculatorService{

    @Override
    public float incomeTaxCalculation(float income) {

        TaxRates taxRate = retrieveTaxRates(income);
        return income*taxRate.rate;
    }

    private TaxRates retrieveTaxRates(float income) {
        TaxRates taxRate = TaxRates.Default;

        if (income <= 14000 && income > 0) {
            taxRate = TaxRates.SB;

        }
        if (income < 0) {
            taxRate = TaxRates.SB;
        }

        if (income > 14000 && income <= 48000) {
            taxRate = TaxRates.S;
        }

        if (income > 48000 && income <= 70000) {
            taxRate = TaxRates.SH;
        }
        if (income > 70000 && income <= 180000) {
            taxRate = TaxRates.ST;
        }

        if (income > 180000) {
            taxRate = TaxRates.SA;
        }

        return taxRate;
    }


}
