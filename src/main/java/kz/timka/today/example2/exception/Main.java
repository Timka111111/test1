package kz.timka.today.example2.exception;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    // try catch, finally, throw, throws, try-with resources
    public static void main(String[] args) {


        int[] arr = {1,3,346,-5,3245};

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < 0) {
                try {
                    throw new NegativeElementException(i, arr[i]);
                } catch (NegativeElementException e) {
                    arr[e.getIndex()] = 0;
                }
            }
        }

        System.out.println(Arrays.toString(arr));

    }








}
