package com.practice.test;

import org.springframework.stereotype.Service;

@Service
public class IncomeTaxCalculatorServiceImp implements IncomeTaxCalculatorService{

    @Override
    public float incomeTaxCalculation(float income) {

        TaxRates taxRate = retrieveTaxRates(income);
        return income*taxRate.rate;
    }

    @Override
    public CalculatorTaxResponse incomeTaxCalculation(CalculatorTaxRequest request) {
        float incomeTax = 0.00f;
        CalculatorTaxResponse response = new CalculatorTaxResponse();
        if (request.getBusinessType() == null || request.getBusinessType() == BusinessTypes.SoleTrader.value || request.getBusinessType().isEmpty()){

            incomeTax = incomeTaxCalculation(request.getIncomeAmount());
        }
        response.setTaxToPay(incomeTax);
        return response;
    }

    private TaxRates retrieveTaxRates(float income) {
        TaxRates taxRate = TaxRates.Default;

        if (income <= 14000) {
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
