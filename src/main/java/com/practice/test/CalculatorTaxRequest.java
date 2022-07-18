package com.practice.test;

import java.util.List;

public class CalculatorTaxRequest {

    private float incomeAmount;

    public float getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(float incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public boolean isNZResident() {
        return isNZResident;
    }

    public void setNZResident(boolean NZResident) {
        isNZResident = NZResident;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    private boolean isNZResident;
    private String businessType;

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    private List <Income> incomes;


    public List<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expenses> expenses) {
        this.expenses = expenses;
    }

    private List <Expenses> expenses;
}

