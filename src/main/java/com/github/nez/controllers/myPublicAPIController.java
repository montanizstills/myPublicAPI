package com.github.nez.controllers;

import com.github.nez.utils.Utils;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping(value = "/public-api-controller")
public class myPublicAPIController {

    @GetMapping(path = "/say-hi")
    public ResponseEntity sayHi() {
        String spokenMessage = "Hello!";
        ResponseEntity responseEntity = new ResponseEntity<>(spokenMessage, HttpStatus.OK);
        return responseEntity;
    }

    @SneakyThrows
    @GetMapping(path = "/chatgpt")
    public ResponseEntity loadPage() {
        return Utils.loadPage("static/chatgpt.html");
    }

    @PostMapping(path = "/chatgpt")
    public void postToModel(@RequestBody String params) {
//        new OpenAIClient("","").makeRequest(CompletionRequest.builder().build(), params);
        System.out.println(params.toString());
    }

    @SneakyThrows
    @GetMapping(path = "request-fields/{requestType}")
    public ResponseEntity getRequestFields(@PathVariable String requestType) {
        // not a big fan of passing in the FQN, would much rather like to pass just {requestType}
        String classPackageName = "com.github.nez.models.requests";
        Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(classPackageName + "." + requestType);
        List fields = new ArrayList();
        for (Field declaredField : clazz.getDeclaredFields()) {
            // fancy code to remove the synthetic fields from the `field` name
            String cleanedFieldName = declaredField.getName().substring(declaredField.getName().indexOf("this$") + 1);
            fields.add(cleanedFieldName);
        }
        return new ResponseEntity<>(fields.toString(), HttpStatus.OK);
    }
}
