package kz.timka.today.example2;

public class Dog extends Animal implements Swimmable, Predator{
    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void swim() {
        System.out.println("swimming");
    }
}
