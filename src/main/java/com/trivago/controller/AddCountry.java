package com.trivago.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/countries")
public class AddCountry {
    @PostMapping("/addCountry")
    public String addCountry(){
        return "done";
    }
}
