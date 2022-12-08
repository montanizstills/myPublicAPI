package com.github.nez.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping(value = "/welcome/")
public class WelcomePageController {
    @GetMapping(path = "/home")
    public ResponseEntity home(){
        String homePage = "<p>/send/request</p>  <span>This api will send a request.</span> <p>/load/request</p> <span>This api will load a request.</span>";
        return new ResponseEntity(homePage, HttpStatus.OK);
    }
}
