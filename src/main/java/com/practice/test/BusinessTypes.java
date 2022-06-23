package com.practice.test;

public enum BusinessTypes {

    SoleTrader("SoleTrader"),
    MostCompanies("MostCompanies"),
    MaoriAuthorities("MaoriAuthorities"),
    NonProfit("NonProfit"),
    Unincorporated("Unincorporated"),
    TrustsAndTrusteesEarned("TrustsAndTrusteesEarned"),

    TrustsAndTrusteesInitial("TrustsAndTrusteesInitial");


    BusinessTypes(String value) {
        this.value = value;
    }

    public final String value;
}
