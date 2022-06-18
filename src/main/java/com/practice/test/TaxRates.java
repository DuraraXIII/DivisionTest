package com.practice.test;

public enum TaxRates {

    Default(0.00f),
    SB(.105f),
    S(.175f),
    SH(.30f),
    ST(.33f),
    SA(.39f);

    TaxRates(float rate) {
        this.rate = rate;
    }

    public final float rate;
}
