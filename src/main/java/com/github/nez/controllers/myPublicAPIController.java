package com.github.nez.controllers;

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
            // send request for information to FrontEnd?
        }

        // create object of type
        String classPackageName = "com.github.nez.models.requests";
        Class<?> cls = Utils.loadClass(classPackageName +"."+ className);

        // get builder method of class and create builder
        Method builderMethod = cls.getDeclaredMethod("builder");
        Object builder = builderMethod.invoke(null);

        // assign values to fields for field of class
        for (Map.Entry<String,String> entry: json.entrySet()){

            String fieldName = entry.getKey();
            String fieldValue = entry.getValue();
            Field field = builder.getClass().getDeclaredField(fieldName);
            field.set(builder,fieldValue);
        }

        // build object
        builderMethod = builder.getClass().getDeclaredMethod("build");
        Object requestObjectOfType = builderMethod.invoke(builder);

        System.out.println(requestObjectOfType);
//         myPublicAPIService.getOpenAIClient().makeRequest(myObjectType, javaMap);

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {

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
