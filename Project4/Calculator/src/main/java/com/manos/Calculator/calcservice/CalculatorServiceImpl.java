package com.manos.Calculator.calcservice;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public double add(List<Double> nums) {

        if (nums == null || nums.isEmpty()) {
            throw new IllegalArgumentException("List must not be empty");
        }

        double sum = 0.0;
        for (int i = 0; i < nums.size(); i++) {
            Double val = nums.get(i);
            sum += val;
        }
        return sum;
    }

    @Override
    public double sub(List<Double> nums) {
        if (nums == null || nums.isEmpty()) {
            throw new IllegalArgumentException("List must not be empty");
        }

        Double val = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            val -= nums.get(i);
        }
        return val;
    }

    @Override
    public double mult(List<Double> nums) {
        if (nums == null || nums.isEmpty()) {
            throw new IllegalArgumentException("List must not be empty");
        }

        double result = 1.0;
        for (int i = 0; i < nums.size(); i++) {
            result *= nums.get(i);
        }
        return result;
    }

    @Override
    public double dia(List<Double> nums) {

        if (nums == null || nums.isEmpty()) {
            throw new IllegalArgumentException("List must not be empty");
        }

        double result = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            double val = nums.get(i);
            if (val == 0) {
                throw new ArithmeticException("Division by zero is not allowed");
            }
            result /= val;
        }
        return result;
    }

}

