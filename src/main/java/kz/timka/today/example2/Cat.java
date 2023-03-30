package kz.timka.today.example2;

public class Cat extends Animal implements SkaloLazit, Predator {
    @Override
    public void lazit() {
        System.out.println("lazit");
    }

}
