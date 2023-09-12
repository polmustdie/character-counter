package com.example.counter.controller;
import com.example.counter.service.CounterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
@RestController
@Validated
public class CounterController {
    @Autowired
    CounterService counterService;
    @GetMapping("/count/{string}")
    @Operation(summary = "Получить список букв с количеством вхождения каждой в строку")
    @ResponseStatus(value = HttpStatus.OK)
    public Map<Character, Integer> count(@PathVariable("string") @Parameter(description = "Входная строка")  @NotBlank String name) {
        return counterService.count(name);
    }
}