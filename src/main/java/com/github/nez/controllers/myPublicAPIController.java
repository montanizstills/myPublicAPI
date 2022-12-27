package com.github.nez.controllers;

import com.github.nez.models.OpenAIClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/public-api-controller")
public class myPublicAPIController {

    @GetMapping(path = "/say-hi")
    public ResponseEntity sayHi() {
        String spokenMessage = "Hello!";
        ResponseEntity responseEntity = new ResponseEntity<>(spokenMessage, HttpStatus.OK);
        return responseEntity;
    }
    @PostMapping(path = "/chatgpt", params = {})
    public ResponseEntity postToModel(){
        new OpenAIClient(System.getenv("openapi-key"),"");
        return null;
    }
}
