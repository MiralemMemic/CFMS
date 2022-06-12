package com.project.apigatewayservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiGatewayController {
    @GetMapping("/load")
    public String load(){
        return "Api Gateway Successfully Loaded";
    }
}
