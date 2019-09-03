package com.resasoftware.nearme.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "Hello there! I'm running.";
    }
}