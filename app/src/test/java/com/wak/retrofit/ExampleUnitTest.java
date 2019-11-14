package com.wak.retrofit;

import com.wak.retrofit.bean.Anim;
import com.wak.retrofit.bean.Engine;
import com.wak.retrofit.bean.LazyResponse;
import com.wak.retrofit.bean.MyInvocation;
import com.wak.retrofit.bean.Person;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void declaring() {
        try {
            Class<? extends LazyResponse> clazz = new LazyResponse().getClass();

            Method method = clazz.getMethod("play");
            method.setAccessible(true);

            //method.getGenericReturnType();//返回该方法返回的数据类型，包括泛型参数
            //method.getDeclaringClass()  获取声明这个方法的类
            System.out.println(method.getDeclaringClass().getSimpleName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void reflect() {
        Person person = new Person();
        Engine anim = (Engine) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(), new MyInvocation(person));

        anim.engine();
    }

    @Test
    public void reflect1() {
        Class<Person> personClass = Person.class;
        try {
            //personClass.getConstructors() 只能获取public修饰的构造器
            Constructor constructor = personClass.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);//Accessible 易理解的，可相处的
            Person person = (Person) constructor.newInstance("呵呵呵呵");

            System.out.println(person.getName());

         /*   for (Constructor con :
                    constructors) {
                con.setAccessible(true);
                int modifiers = con.getModifiers();

                System.out.println(modifiers + ":::" + con.getDeclaringClass().getSimpleName());
            }*/

            Field field = personClass.getDeclaredField("age");
            field.setAccessible(true);
            //当获取的字段为static的时候可以获取到数据
            String age = (String) field.get(null);
            System.out.println(age);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}