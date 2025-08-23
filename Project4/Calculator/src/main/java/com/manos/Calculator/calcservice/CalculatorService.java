package com.manos.Calculator.calcservice;

import java.util.List;

public interface CalculatorService {

    double add(List<Double> nums);

    double sub(List<Double> nums);

    double mult(List<Double> nums);

    double dia(List<Double> nums);
}
