package kz.timka.today.example2;

public class Colibri extends Animal implements SuperFlyable, Swimmable, Predator{

    public Colibri(String name) {
        this.name = name;
    }

    @Override
    public void swim() {
        System.out.println("swimming");
    }

    @Override
    public void fly() {

    }

    @Override
    public void superFly() {

    }
}
