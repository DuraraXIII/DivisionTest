package com.practice.test;

import org.springframework.stereotype.Service;

@Service
public class IncomeTaxCalculatorServiceImp implements IncomeTaxCalculatorService{

    @Override
    public float incomeTaxCalculation(float income) {

        float taxRate = 0;

        if (income <= 14000 && income > 0) {
            taxRate = SB;

        }
        if (income < 0) {
            taxRate = SB;
        }

        if (income > 14000 && income <= 48000) {
            taxRate = S;
        }

        if (income > 48000 && income <= 70000) {
            taxRate = SH;
        }
        if (income > 70000 && income <= 180000) {
            taxRate = ST;
        }

        if (income > 180000) {
            taxRate = SA;
        }



        return income * taxRate;
    }
}
