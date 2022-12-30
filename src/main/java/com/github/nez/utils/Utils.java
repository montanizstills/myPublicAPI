package com.github.nez.utils;

public class Utils {
//    TODO - fix so that any input returns FooBar - pascal case, then use in MyPublicApiController.getRequestFields()
    public static String toPascalCase(String input){
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
