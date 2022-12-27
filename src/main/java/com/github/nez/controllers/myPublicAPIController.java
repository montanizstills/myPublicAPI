package com.github.nez.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/public-api-controller")
public class myPublicAPIController {

    @GetMapping(path = "/say-hi")
    public ResponseEntity sayHi() {
        String spokenMessage = "Hello!";
        ResponseEntity responseEntity = new ResponseEntity<>(spokenMessage, HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping(path = "/chatgpt")
    public ResponseEntity loadPage(){
        return new ResponseEntity<String>("<form>\n" +
                "  <textarea id=\"text-input\"></textarea>\n" +
                "  <button type=\"submit\">Submit</button>\n" +
                "</form><script>const form = document.querySelector('form');\n" +
                "form.addEventListener('submit', handleSubmit);function handleSubmit(event) {\n" +
                "  event.preventDefault();\n" +
                "\n" +
                "  const textInput = document.querySelector('#text-input');\n" +
                "  const text = textInput.value;\n" +
                "\n" +
                "  console.log(text);\n" +
                "}</script>",HttpStatus.OK);
    }
    @PostMapping(path = "/chatgpt", params = {})
    public ResponseEntity postToModel(@PathVariable String... myEndpointParams){
//        new OpenAIClient(System.getenv("openapi-key"),"").makeRequest(CompletionRequest.class, myEndpointParams);
        return null;
    }
}
