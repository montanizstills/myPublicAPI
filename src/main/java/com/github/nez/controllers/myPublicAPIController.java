package com.github.nez.controllers;

import com.github.nez.utils.Utils;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping(value = "/public-api-controller")
public class myPublicAPIController {

    @GetMapping(path = "/say-hi")
    public ResponseEntity<String> sayHi() {
        String spokenMessage = "Hello!";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(spokenMessage, HttpStatus.OK);
        return responseEntity;
    }

    @SneakyThrows
    @GetMapping(path = "/chatgpt")
    public ResponseEntity<String> loadPage() {
        return Utils.loadPage("static/chatgpt.html");
    }

    @SneakyThrows
    @PostMapping(path = "/chatgpt")
    public void postToModel(@RequestBody final String params) {

        //extract request type name from params.
        Map<String, String> json = new Gson().fromJson(params, Map.class);
        String className = json.get("requestType_input_field"); // TODO - investigate why this call returns null, does the client pass correctly?

        System.out.println(className);
//        String classPackageName = "com.github.nez.models.requests";
//        Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(classPackageName + "." + className);
//        Method method = clazz.getMethod("createRequest", Map.class);
//        Object myObjectType = method.invoke(null,javaMap);
//        myPublicAPIService.getOpenAIClient().makeRequest(myObjectType, javaMap);
    }

    @SneakyThrows
    @GetMapping(path = "request-fields/{requestType}")
    public ResponseEntity<String> getRequestFields(@PathVariable String requestType) {
        // not a big fan of passing in the FQN, would much rather like to pass just {requestType}
        String classPackageName = "com.github.nez.models.requests";
        Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(classPackageName + "." + requestType);
        List<String> fields = new ArrayList<>();
        for (Field declaredField : clazz.getDeclaredFields()) {
            // fancy code to remove the synthetic fields from the `field` name
            String cleanedFieldName = declaredField.getName().substring(declaredField.getName().indexOf("this$") + 1);
            fields.add(cleanedFieldName);
        }
        return new ResponseEntity<>(fields.toString(), HttpStatus.OK);
    }
}
