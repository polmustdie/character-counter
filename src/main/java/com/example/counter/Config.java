package com.example.counter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Character counter Api",
                description = "Считает число вхождения каждой буквы в строку", version = "1.0.0"
        )
)
public class Config {

}

