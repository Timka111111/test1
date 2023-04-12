package kz.timka.today.example2.collection;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        List<String> names = getNames();

        System.out.println(names);

    }

    public static List<String> getNames() {
        return Collections.unmodifiableList(new LinkedList<>(Arrays.asList("Bob", "John")));
    }
}
