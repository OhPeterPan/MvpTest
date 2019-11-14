package com.wak.retrofit.bean;

public class Person implements Anim, Engine {
    private String name = "大哥我错了";
    private static  String age = "1";

    private Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    @Override
    public void play() {
        System.out.println("person play");
    }

    @Override
    public void engine() {
        System.out.println("person engine");
    }
}
