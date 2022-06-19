package com.practice.test;

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

}
