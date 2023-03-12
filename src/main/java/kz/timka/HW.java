package kz.timka;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HW {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        char[] symbols = {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'};

        String input = "";
        int rand = random.nextInt(words.length);

        String word = words[rand];
        System.out.println(word);

        while(true) {
            input = scanner.nextLine();
            if(equals(input, word)) {
                System.out.println("You won");
                break;
            }

            char[] r = toCharArray(word);
            char[] t = toCharArray(input);

            for (int i = 0; i < r.length; i++) {
                for (int j = 0; j < t.length; j++) {
                    if(r[i] == t[j]) {
                        symbols[i] = r[i];
                    }
                }
            }

            System.out.println(Arrays.toString(symbols));
        }
    }

    public static boolean equals(String str1, String str2) {
        if(str1.length() != str2.length()) {
            return false;
        }

        for (int i = 0; i < str1.length(); i++) {
            if(str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;


    }

    public static char[] toCharArray(String str) {
        char[] arr = new char[str.length()];

        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i);
        }
        return arr;
    }


}
