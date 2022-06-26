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
        boolean isIndividual = request.getBusinessType() == null || request.getBusinessType().isEmpty();
        if (isIndividual) {
            return incomeTaxCalculatorForIndividuals(request);
        }
            return  incomeTaxCalculatorForBusiness(request);
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


    private CalculatorTaxResponse incomeTaxCalculatorForBusiness(CalculatorTaxRequest request) {
        float incomeTax = 0.00f;
        CalculatorTaxResponse response = new CalculatorTaxResponse();
        BusinessTypes businessTypes = getBusinessTypeEnum(request.getBusinessType());
        if (businessTypes == null){
            throw new RuntimeException("Invalid BusinessType");
        }
        if (businessTypes == BusinessTypes.SoleTrader || businessTypes == BusinessTypes.Unincorporated){

            incomeTax = incomeTaxCalculation(request.getIncomeAmount());
        }
        if (businessTypes == BusinessTypes.MostCompanies|| businessTypes == BusinessTypes.NonProfit){
            incomeTax = request.getIncomeAmount()*.28f;

        }
        if (businessTypes == BusinessTypes.MaoriAuthorities){
            incomeTax = request.getIncomeAmount()*.175f;

        }

        if (businessTypes == BusinessTypes.TrustsAndTrusteesEarned){
            incomeTax = request.getIncomeAmount()*.33f;

        }
        if (businessTypes == BusinessTypes.TrustsAndTrusteesInitial){
            incomeTax = request.getIncomeAmount()*0.00f;

        }

        response.setTaxToPay(incomeTax);
        return response;
    }
    private BusinessTypes getBusinessTypeEnum(String value) {
        BusinessTypes businessTypes = null;
        try {
            businessTypes = Enum.valueOf(BusinessTypes.class, value);
        }catch (Exception e){

        }
        return businessTypes;
    }


    private CalculatorTaxResponse incomeTaxCalculatorForIndividuals(CalculatorTaxRequest request) {
        float incomeTax = 0.00f;
        CalculatorTaxResponse response = new CalculatorTaxResponse();

            incomeTax = incomeTaxCalculation(request.getIncomeAmount());
            response.setTaxToPay(incomeTax);

        return response;
    }

}
