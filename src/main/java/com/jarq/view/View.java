package com.jarq.view;

import java.util.Collection;
import java.util.Map;

public class View {

    public static void print(Map<String,Integer> dictionary) {
        dictionary.entrySet().forEach(System.out::println);
    }

    public static void print(Collection<String> collection) {
        collection.forEach(System.out::println);
    }

    public static void print(Number num) {
        System.out.println(num);
    }

    public static void print(String text) {
        System.out.println(text);
    }
}
