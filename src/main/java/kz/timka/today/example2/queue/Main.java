package kz.timka.today.example2.queue;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();



        Set<String> set = new HashSet<>();
        set.add("G");
        set.add("T");
        set.add("A");
        set.add("E");


        System.out.println(set.size());
        System.out.println(set);



    }

    public static void exampleArrayDequeAsStack() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.push("first");
        deque.push("second");
        deque.pop();
        System.out.println(deque);
    }

    public static void exampleArrayDequeAsQueue() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.offer("A");
        deque.offerFirst("B");
        deque.offer("C");
        System.out.println(deque);
    }



}
