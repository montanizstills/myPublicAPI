package com.github.nez.controllers;

import com.github.nez.models.requests.CompletionRequest;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        ClassLoader classLoader = getClass().getClassLoader();
        URL fileUrl = classLoader.getResource("static/chatgpt.html");
        if (fileUrl == null) {
            // Return a NOT_FOUND response if the file was not found
            return new ResponseEntity<>("cannot find/load file!", HttpStatus.NOT_FOUND);
        }
        // Read the html file from the URL
        Path filePath = Paths.get(fileUrl.toURI());
        String html = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
        return new ResponseEntity<>(html, HttpStatus.OK);
    }

    @PostMapping(path = "/chatgpt")
    public void postToModel(@RequestBody String params) {
//        new OpenAIClient("","").makeRequest(CompletionRequest.builder().build(), params);
        System.out.println(params.toString());
    }

    @GetMapping(path = "completion-request-fields")
    public ResponseEntity getCompletionRequestFields() {
        List fields = new ArrayList();
        for (Field declaredField : CompletionRequest.class.getDeclaredFields()) {
            // fancy code to remove the synthetic fields from the `field` name
            String cleanedFieldName = declaredField.getName().substring(declaredField.getName().indexOf("this$") + 1);
            fields.add(cleanedFieldName);
        }
        return new ResponseEntity<>(fields.toString(), HttpStatus.OK);
    }
}
