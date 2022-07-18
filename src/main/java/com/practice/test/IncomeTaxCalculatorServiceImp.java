package com.practice.test;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class IncomeTaxCalculatorServiceImp implements IncomeTaxCalculatorService {

    @Override
    public float incomeTaxCalculation(float income) {
        TaxRates taxRate = retrieveTaxRates(income);
        return income * taxRate.rate;
    }


    @Override
    public CalculatorTaxResponse incomeTaxCalculation(CalculatorTaxRequest request) {
        boolean isIndividual = request.getBusinessType() == null || request.getBusinessType().isEmpty();
        if (isIndividual) {
            return incomeTaxCalculatorForIndividuals(request);
        }
        else    return incomeTaxCalculatorForBusiness(request);
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
        if (businessTypes == null) {
            throw new RuntimeException("Invalid BusinessType");
        }
        if (businessTypes == BusinessTypes.SoleTrader || businessTypes == BusinessTypes.Unincorporated) {

            incomeTax = incomeTaxCalculation(request.getIncomeAmount());
        }
        if (businessTypes == BusinessTypes.MostCompanies || businessTypes == BusinessTypes.NonProfit) {
            incomeTax = request.getIncomeAmount() * .28f;

        }
        if (businessTypes == BusinessTypes.MaoriAuthorities) {
            incomeTax = request.getIncomeAmount() * .175f;

        }

        if (businessTypes == BusinessTypes.TrustsAndTrusteesEarned) {
            incomeTax = request.getIncomeAmount() * .33f;

        }
        if (businessTypes == BusinessTypes.TrustsAndTrusteesInitial) {
            incomeTax = request.getIncomeAmount() * 0.00f;

        }


        response.setTaxToPay(incomeTax);
        return response;
    }

    private BusinessTypes getBusinessTypeEnum(String value) {
        BusinessTypes businessTypes = null;
        try {
            businessTypes = Enum.valueOf(BusinessTypes.class, value);
        } catch (Exception e) {

        }
        return businessTypes;
    }


    private CalculatorTaxResponse incomeTaxCalculatorForIndividuals(CalculatorTaxRequest request) {
        float incomeTax = 0.00f;

        CalculatorTaxResponse response = new CalculatorTaxResponse();

        incomeTax = incomeTaxCalculation(request.getIncomeAmount());
        response.setTaxToPay(incomeTax);

        if (request.getIncomes() != null) {
            float incomes = (float) request.getIncomes().stream().mapToDouble(Income::getAmount).sum();
            float expenses = (float) request.getExpenses().stream().mapToDouble(Expenses::getExpensesAmount).sum();
            float netIncomes = incomes - expenses;
            incomeTax = incomeTaxCalculation(netIncomes);
            response.setTaxToPay(incomeTax);

        }


        if (request.getIncomes() != null && request.getIncomes().isEmpty()) {
            throw new RuntimeException("Income List is Empty");
        }

        if (request.getIncomes() != null) {
            float incomes = (float) request.getIncomes().stream().mapToDouble(Income::getAmount).sum();
            if (incomes < 0) {
                throw new RuntimeException("IncomeAmount Contains Negative Values");
            }
            if (incomes == 0) {
                throw new RuntimeException("IncomeAmount cannot be zero");
            }
            boolean incomeTypeIsNull = request.getIncomes().stream().anyMatch(income -> income.getIncomeType() == null);
            if (incomeTypeIsNull) {
                throw new RuntimeException("IncomeType is Null");
            }
            if (request.getIncomes().isEmpty()) {
                throw new RuntimeException("Income List is Empty");
            }
            boolean incomeTypeContainsSpecialCharacters = request.getIncomes().stream().anyMatch(income -> income.getIncomeType().contains("'!<>'"));
            if (incomeTypeContainsSpecialCharacters) {
                throw new RuntimeException("IncomeType Contains Special Characters");
            }

            boolean incomeTypeIsEmpty = request.getIncomes().stream().anyMatch(income -> income.getIncomeType().isEmpty());
            if (incomeTypeIsEmpty) {
                throw new RuntimeException("IncomeType is empty");
            }

            boolean incomeTypeExceedMaxCharacter = request.getIncomes().stream().anyMatch(income -> income.getIncomeType().length() + 1 > 250);
            if (incomeTypeExceedMaxCharacter) {
                throw new RuntimeException("IncomeType Exceed Character Limit Of 250");
            }

        }


        return response;
    }




}
