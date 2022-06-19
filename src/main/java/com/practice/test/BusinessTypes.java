package com.practice.test;

public enum BusinessTypes {

    SoleTrader("SoleTrader"),
    MostCompanies("MostCompanies"),
    MaoriAuthorities("MaoriAuthorities"),
    NonProfit("NonProfit"),
    Unincorporated("Unincorporated"),
    TrustsAndTrustees("TrustsAndTrustees");


    BusinessTypes(String value) {
        this.value = value;
    }

    public final String value;
}
