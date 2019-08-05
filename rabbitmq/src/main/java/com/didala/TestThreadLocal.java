package com.didala;

import java.util.function.Supplier;

public class TestThreadLocal {


    public static void main(String[] args) {
        ThreadLocal<String> objectThreadLocal = new ThreadLocal<>();

        objectThreadLocal.set("dddd");

        String s = objectThreadLocal.get();
        System.out.println(s);

        ThreadLocal<String> local = ThreadLocal.withInitial(() -> "test");

        String s1 = local.get();

        System.out.println(s1);
    }
}
