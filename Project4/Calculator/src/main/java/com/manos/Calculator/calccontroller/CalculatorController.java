package com.manos.Calculator.calccontroller;

import com.manos.Calculator.calcservice.CalculatorService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/add")
    public double add(@RequestParam @NotEmpty List<@NotNull Double> nums) {
        return calculatorService.add(nums);
    }

    @GetMapping("/sub")
    public double sub(@RequestParam @Size(min = 2) List<@NotNull Double> nums) {
        return calculatorService.sub(nums);
    }

    @GetMapping("/mult")
    public double mult(@RequestParam @NotEmpty List<@NotNull Double> nums) {
        return calculatorService.mult(nums);
    }

    @GetMapping("/dia")
    public double dia(@RequestParam @Size(min = 2) List<@NotNull Double> nums) {
        return calculatorService.dia(nums);
    }

}
