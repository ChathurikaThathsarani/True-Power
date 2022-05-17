package com.example.truepower;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnergyCalculationTest {
    private EnergyCalculation energyCalculation;
    @BeforeEach
    public void setup(){
        energyCalculation=new EnergyCalculation();
    }
    @Test
    public void testJoulesEnergy(){
        float result=energyCalculation.calculateEnergyToJoules(1200F);
        assertEquals(5024.16015625,result);
    }
    @Test
    public void testKiloJoulesEnergy(){
        float result=energyCalculation.calculateEnergyToKiloJoules(1200F);
        assertEquals(5.024159908294678,result);
    }
}
