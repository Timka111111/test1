package kz.timka.today.example2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoxWithNumbers<N extends Number> {
    private List<N> numbers;


    public BoxWithNumbers(N... items) {
        numbers = new ArrayList<>(Arrays.asList(items));

    }

    public double sum() {
        double res = 0.0;
        for (int i = 0; i < numbers.size(); i++) {
            res += numbers.get(i).doubleValue();
        }

        return res;
    }

    public boolean compareBySum(BoxWithNumbers<?> another) {
        return Math.abs(this.sum() - another.sum()) < 0.0001;
    }

}
