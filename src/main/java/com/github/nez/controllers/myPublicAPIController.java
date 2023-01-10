package com.github.nez.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nez.models.interfaces.IOpenAIRequest;
import com.github.nez.utils.Utils;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        // extract request type name from params.
        final Map<String, String> json = new Gson().fromJson(params, Map.class);
        final String className = json.get("requestType_input_field");

        // null check
        if ("".equalsIgnoreCase(className) || className == null) {
            throw new Error("OpenAI request type must not be null!");
        }

        // get list of classes of IOpenAIRequest type
//        List<Class<?>> classTypeList = Stream.of(Package.getPackages())
//                .flatMap(pkg -> Stream.of(pkg.getName())) // convert package into string representation of its name
//                .filter(pkgName -> pkgName.equals("com.github.nez"))
//                .flatMap(pkgName -> Utils.getAllClassesFromPackage(pkgName).stream())
//                .filter(IOpenAIRequest.class::isAssignableFrom) // filter
//                .collect(Collectors.toList());
//

        // create object of type
        String classPackageName = "com.github.nez.models.requests";
        Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(classPackageName + "." + className);
        Method method = clazz.getMethod("createRequest", Map.class);
        Object myObjectType = method.invoke(null, json);

//         myPublicAPIService.getOpenAIClient().makeRequest(myObjectType, javaMap);

    }

    @SneakyThrows
    @GetMapping(path = "request-fields/{requestType}")
    public ResponseEntity<String> getRequestFields(@PathVariable final String requestType) {
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
