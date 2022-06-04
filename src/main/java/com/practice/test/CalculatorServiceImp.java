package com.practice.test;

import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImp implements CalculatorService {


    @Override
    public int addition(int value1, int value2) {
        return 0;
    }

    @Override
    public int subtraction(int value1, int value2) {
        return 0;
    }

    @Override
    public int multiplication(int value1, int value2) {
        return 0;
    }

    @Override
    public int division(int dividend, int divisor) {

        if (divisor == 0 || dividend < 0 || divisor < 0) {
            return 0;
        }

            return dividend / divisor;

        }

}
