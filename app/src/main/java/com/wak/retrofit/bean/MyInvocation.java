package com.wak.retrofit.bean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocation implements InvocationHandler {
    Person person;

    public MyInvocation(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println(method);
        Object o1 = method.invoke(person, objects);
        return o1;
    }
}
