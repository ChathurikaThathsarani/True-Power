package com.example.truepower;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NextCheckupDateTest {
    private NextCheckupDate healthAdapter;
    @BeforeEach
    public void calculate(){
        healthAdapter= new NextCheckupDate  ();
    }
    @Test
    public void TestCheckup(){
        float results = healthAdapter.nDays_Between_Dates("03/12/2022","05/20/2022");
        assertEquals(69.0,results);
    }

}
