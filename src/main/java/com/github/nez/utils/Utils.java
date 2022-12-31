package com.github.nez.utils;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    @SneakyThrows
    public static ResponseEntity <String>loadPage(String path) {
        // could literally be any class --
        // the classLoader.getResource will handle the rest, it'd be interesting to know how this works
        // Module.class.getClassLoader().getResource(""); -- need JDK9
        ClassLoader classLoader = Utils.class.getClassLoader();
        URL fileUrl = classLoader.getResource(path);
        if (fileUrl == null) {
            // Return a NOT_FOUND response if the file was not found
            return new ResponseEntity<>("cannot find/load file!", HttpStatus.NOT_FOUND);
        }
        // Read the html file from the URL
        Path filePath = Paths.get(fileUrl.toURI());
        String html = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
        return new ResponseEntity<>(html, HttpStatus.OK);
    }

    //    TODO - fix so that any input returns FooBar - pascal case, then use in MyPublicApiController.getRequestFields()
    public static String toPascalCase(String input) {
        String[] words = input.split("[^\\p{L}\\p{Nd}]+");
        String[] pascalWords = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String pascalWord = word.substring(0, 1).toUpperCase() + word.substring(1);
            pascalWords[i] = pascalWord;
            String pascalClassName = String.join("", pascalWords);
            return pascalClassName;
        }
        return null;
    }
}
