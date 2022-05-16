package com.example.truepower;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BMICalculationTest {
    private BMICalculation bmiCalculation;
    @BeforeEach
    public void calculate(){
        bmiCalculation = new BMICalculation();
    }
    @Test
    public void TestBMI(){
        float results = bmiCalculation.calculateBMI(1.6f,60);
        assertEquals(23.437498092651367,results);
    }



}
