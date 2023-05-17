package kz.timka;

import kz.timka.today.example2.*;

import java.util.*;

public class App {
    public static void main(String[] args) {

    }




    public static <T> T getFirstElement(List<T> list) {
        return list.get(0);
    }

    public static double avg(List<? extends Number> list) {
        double result = 0.0;

        for (int i = 0; i < list.size(); i++) {
            result += list.get(i).doubleValue();
        }

        return result / list.size();
    }


}
