package com.example.truepower;

public class EnergyCalculation {

    protected float calculateEnergyToJoules(Float value){
        float ans = (float) (value * 4.1868);
        return ans;
    }
    protected float calculateEnergyToKiloJoules(Float value){
        float ans = (float) ((value * 4.1868)/1000);
        return ans;
    }
}

