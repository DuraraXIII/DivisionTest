package com.practice.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/taxCalculator")

public class TaxCalculatorController {

    @Autowired
    private IncomeTaxCalculatorService incomeTaxCalculatorService;

    @GetMapping
    public String get() {
        return "Hello World API";
    }

    @PostMapping
    public CalculatorTaxResponse calculateTax(@Validated @RequestBody CalculatorTaxRequest request) {
        return incomeTaxCalculatorService.incomeTaxCalculation(request);
    }


}
