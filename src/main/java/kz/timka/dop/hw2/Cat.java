package kz.timka.dop.hw2;

import java.util.Objects;

public class Cat extends Animal {

    public Cat(String name) {
        this.name = name;
        this.maxRunDistance = 200;
        this.maxSwimDistance = 0;
        this.type = "cat";
    }

}
