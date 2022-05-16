package com.example.truepower;

public class BMRCalculation {

    public double femaleBMR(int age, float height, float weight){
        double answer;
        answer=88.4+(13.4*weight)+(4.8*height)-(5.68*age);
        return answer;
    }

    public double maleBMR(int age, float height, float weight){
        double answer;
        answer=447.6+(9.25*weight)+(3.10*height)-(4.33*age);
        return answer;
    }

}
