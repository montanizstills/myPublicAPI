package com.github.nez.utils;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Utils {

    @SneakyThrows
    public static Class<?> loadClass(String className) {
        return ClassLoader.getSystemClassLoader().loadClass(className);
    }

    @SneakyThrows
    public static List<Class<?>> getAllClassesFromPackage(String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    @SneakyThrows
    private static List<Class<?>> findClasses(File directory, String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    @SneakyThrows
    public static ResponseEntity<String> loadPage(String path) {
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

}
