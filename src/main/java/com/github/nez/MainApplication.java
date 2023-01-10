package com.github.nez;

import com.github.nez.models.interfaces.IOpenAIRequest;
import com.github.nez.utils.Utils;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

@SpringBootApplication
public class MainApplication {
    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}

