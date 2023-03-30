package kz.timka.dop;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static Random random = new Random();
    static String heroName;
    static  int heroHp,heroHpMax , heroAttack;
    static int heroClass;

    static String[] classesName = {"", "Варвар", "Рыцарь", "Мудрец"};
    static String monsterName;
    static int monsterHp, monsterMaxHp, monsterAttack, monsterClass;
    static int travelDirection;
    public static void main(String[] args) {

//        String[] arr = {"f", "d", "c"};
//        int rand = random.nextInt(arr.length);
//        System.out.println(arr[rand]);
        selectName();
        selectClass();
        System.out.println("Ваше приключение начинается");
        printPlayerInfo();
        travelPhrase();
        if(travelDirection == 2) {
            battlePhase();
        } else {
            System.out.println("вы прошли игру");
        }

    }

    public static void battlePhase() {
        generateMonster();
        System.out.println("Вы встречаете злобного монстра");
        printMonsterInfo();
        do {
            int playerAction;
//            do {
//
//                System.out.println("Ход игрока 1 - Атаковать 2 - Защититься");
//                playerAction = sc.nextInt();
//            }while (playerAction < 1 || playerAction > 2);

            playerAction = showUserAction("", "Атаковать", "Защититься");

            if(playerAction == 1) {
                monsterHp -= heroAttack;
                System.out.println(monsterName + " получил ед.урона " + heroAttack);

            }
            if(playerAction == 2) {
                if(random.nextInt(100) < 20) {
                    heroHp++;
                    if(heroHp > heroHpMax) {
                        heroHp = heroHpMax;
                    }
                    System.out.println("Герой восстановил здоровье");
                } else {
                    System.out.println("Герой просто переждал");
                }
            }
            System.out.println("Ход монстра");
            heroHp -= monsterAttack;
            System.out.println(heroName + " получил ед.урона " + monsterAttack);

            System.out.println("состояние участников");
            printPlayerInfo();
            printMonsterInfo();


        }while (monsterHp > 0 && heroHp > 0);
    }

    public static void travelPhrase() {
        travelDirection = showUserAction("", "Лес", "Пещера", "Горы");
//        do {
//            System.out.println("1. Лес");
//            System.out.println("2. Пещера");
//            System.out.println("3. Горы");
//            travelDirection = sc.nextInt();
//        }while (travelDirection < 1 || travelDirection > 3);
    }



    public static void generateMonster() {
        int n = random.nextInt(2);
        switch (n) {
            case 0:
                monsterName = "Goblin";
                monsterMaxHp = 7;
                monsterHp = monsterMaxHp;
                monsterAttack = 1;
                break;
            case 1:
                monsterName = "Ogre";
                monsterMaxHp = 9;
                monsterHp = monsterMaxHp;
                monsterAttack = 2;
                break;
        }
    }

    public static void printPlayerInfo() {
        System.out.println("Class: " + classesName[heroClass] + " Name: " + heroName);
        System.out.println("HP: " + heroHp + "/" + heroHpMax + " Attack: " + heroAttack);
    }

    public static void printMonsterInfo() {
        System.out.println("Name: " + monsterName);
        System.out.println("HP: " + monsterHp + "/" + monsterMaxHp + " Attack: " + monsterAttack);
    }

    public static void selectClass() {
//        do {
//            System.out.println("Представься пожалуйста, каково твое призвание");
//            System.out.println("1. Варвар");
//            System.out.println("2. Рыцарь");
//            System.out.println("3. Мудрец");
//            heroClass = sc.nextInt();
//        }while (heroClass < 1 || heroClass > 3);
        heroClass = showUserAction("Представься пожалуйста, каково твое призвание",
                "Варвар", "Рыцарь", "Мудрец");

        switch (heroClass) {
            case 1:
                heroHpMax = 5;
                heroAttack = 2;
                break;
            case 2:
                heroHpMax = 8;
                heroAttack = 1;
                break;
            case 3:
                heroHpMax = 4;
                heroAttack = 3;
                break;
        }
        heroHp = heroHpMax;
    }

    public static void selectName() {
        System.out.println("Вы начинаете свое путешетсвие. Введите свое имя");
        heroName = sc.nextLine();
    }

    public static int showUserAction(String question, String... variants) {
        int userInput;
        do {
            System.out.println(question);
            for (int i = 0; i < variants.length; i++) {
                System.out.println((i + 1) + ". " + variants[i]);
            }
            userInput = sc.nextInt();
        }while (userInput < 1 || userInput > variants.length);

        return userInput;
    }
}