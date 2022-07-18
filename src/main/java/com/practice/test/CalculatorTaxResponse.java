package com.practice.test;

public class CalculatorTaxResponse {

    public float getTaxToPay() {
        return taxToPay;
    }

    public void setTaxToPay(float taxToPay) {
        this.taxToPay = taxToPay;
    }

    private float taxToPay;

    private float totalIncome;

    public float getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(float totalIncome) {
        this.totalIncome = totalIncome;
    }


    public float getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(float totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    private float totalExpenses;
}

