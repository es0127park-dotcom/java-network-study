package com.mtcoding.ex11;

import lombok.Data;

import java.util.ArrayList;

@Data
class Person {
    private String name;
    private int age;
    private ArrayList<String> hobbys;


    public void 세월이가면(){
        age = age + 1;
    }

    public Person(String name, int age, ArrayList<String> hobbys) {
        this.name = name;
        this.age = age;
        this.hobbys = hobbys;
    }
}

public class DataTest {
    public static void main(String[] args) {
        ArrayList<String> hobbys = new ArrayList<>();
        hobbys.add("농구");
        hobbys.add("축구");
        Person p = new Person("홍길동", 14, hobbys);
        System.out.println(1);

        p.세월이가면();
        p.세월이가면();

        System.out.println(p.getAge());
        System.out.println(p.getName());

        for (int i = 0; i < p.getHobbys().size(); i++) {
            System.out.println(p.getHobbys().get(i));
        }
    }
}