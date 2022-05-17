package com.example.truepower;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BMRCalculationTest {
    private BMRCalculation bmrCalculation;
    @BeforeEach
    public void setup(){
        bmrCalculation=new BMRCalculation();
    }
    @Test
    public void testFemaleBMR(){
        double result=bmrCalculation.femaleBMR(22,160,60);
        assertEquals(1535.44,result);
    }
    @Test
    public void testMaleBMR(){
        double result=bmrCalculation.maleBMR(22,160,60);
        assertEquals(1403.34,result);
    }
}
