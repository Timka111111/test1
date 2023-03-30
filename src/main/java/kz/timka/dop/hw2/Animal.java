package kz.timka.dop.hw2;

public abstract class Animal {
    String name;
    String type;
    int maxRunDistance;
    int maxSwimDistance;

    public void run(int distance) {
        if(distance < maxRunDistance) {
            System.out.println(type + " пробежал");
        }else {
            System.out.println(type + " не смог пробежать");
        }
    }

    public  void swim(int distance) {
        if(maxSwimDistance == 0) {
            System.out.println(type + " не умеет плавать");
            return;
        }
        if(distance < maxSwimDistance) {

            System.out.println(type + " проплыла");
        }else {
            System.out.println( type + " не смогла проплыть");
        }
    }
}
