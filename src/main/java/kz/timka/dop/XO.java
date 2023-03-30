package kz.timka.dop;

import java.util.Random;
import java.util.Scanner;
// git bash
public class XO {
    static char[][] map;
    static final int SIZE = 3;
    static final char DOT_PLAYER = 'X';
    static final char DOT_AI = '0';

    static final char DOT_EMPTY = '*';

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();


    public static void main(String[] args) {
        prepareMap();
        printMap();
        while (true) {
            playerTurn();
            printMap();
            if(checkWin(DOT_PLAYER)) {
                System.out.println("Победил игрок");
                break;
            }
            if(isMapFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
            if(checkWin(DOT_AI)) {
                System.out.println("Победил ИИ");
                break;
            }
            if(isMapFull()) {
                System.out.println("Ничья");
                break;
            }

        }
        // 1) заменить на циклы метод checkWin
        // 2)***
        // блокировать ход игрока чтобы он не выиграл
        // найти вариант где можно выиграть, если уже допустим бот случайно поставил на 1 1, 2 2 то тогда
        // бот должен поставить на 3 3

        System.out.println("Игра завершена!");

    }

    // 0 0 1 2
    // 0 * * *
    // 1 * * *
    // 2 * * *
    public static boolean checkWin(char dot) {
        boolean firstDiagonal = true;
        boolean secondDiagonal = true;
        boolean horizontal;
        boolean vertical;

        for (int i = 0; i < SIZE; i++) {
            firstDiagonal = (map[i][i] == dot) && firstDiagonal;
            secondDiagonal = (map[i][SIZE - i - 1] == dot) && secondDiagonal;
        }
        if(firstDiagonal || secondDiagonal) return true;

        for (int i = 0; i < SIZE; i++) {
            horizontal = true;
            vertical = true;
            for (int j = 0; j < SIZE; j++) {
                horizontal = map[j][i] == dot && horizontal;
                vertical = map[i][j] == dot && vertical;
            }
            if(vertical || horizontal) return true;
        }

        return false;





//        if(map[0][0] == dot && map[0][1] == dot && map[0][2] == dot) {
//            return true;
//        }
//        if(map[1][0] == dot && map[1][1] == dot && map[1][2] == dot) {
//            return true;
//        }
//        if(map[2][0] == dot && map[2][1] == dot && map[2][2] == dot) {
//            return true;
//        }
//
//        if(map[0][0] == dot && map[1][0] == dot && map[2][0] == dot) {
//            return true;
//        }
//
//        if(map[0][1] == dot && map[1][1] == dot && map[2][1] == dot) {
//            return true;
//        }
//
//        if(map[0][2] == dot && map[1][2] == dot && map[2][2] == dot) {
//            return true;
//        }

//        if(map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) {
//            return true;
//        }
//
//        if(map[2][0] == dot && map[1][1] == dot && map[0][2] == dot) {
//            return true;
//        }


   //     return false;

    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void aiTurn() {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(isCellEmpty(j, i)) {
                    setSymbol(j, i, DOT_AI);
                    if(checkWin(DOT_AI)) {
                        return;
                    }
                    setSymbol(j,i, DOT_EMPTY);
                }
            }
        } // ищет себе место для выигрыша


        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(isCellEmpty(j, i)) {
                    setSymbol(j, i, DOT_PLAYER);
                    if(checkWin(DOT_PLAYER)) {
                        setSymbol(j,i,DOT_AI);
                        return;
                    }
                    setSymbol(j, i, DOT_EMPTY);
                }
            }
        }


        int x, y;
        do {
            x = random.nextInt(SIZE); // 0 1 2
            y = random.nextInt(SIZE);
        }while (!isCellEmpty(x, y));
        map[x][y] = DOT_AI;
        System.out.printf("Ход ИИ: x = %d, y = %d\n", (x + 1), (y + 1));
    }

    public static void setSymbol(int x, int y, char sym) {
        map[x][y] = sym;
    }

    public static void playerTurn() {
        int x, y;
        do {
            System.out.println("Укажите координаты хода в формате 'x y'");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }while (!isCellEmpty(x, y));
        map[x][y] = DOT_PLAYER;
    }

    public static boolean isCellEmpty(int x, int y) {
        if(x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        if(map[x][y] == DOT_EMPTY) {
            return true;
        }
        return false;
    }


    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) { // отрисовка нумерации сверху
            System.out.print(i + " ");
        }
        System.out.println();

        for (int y = 0; y < SIZE; y++) {
            System.out.print((y + 1) + " "); // нумерация сбоку
            for (int x = 0; x < SIZE; x++) { // сама распечатка карты
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }

    }

    public static void prepareMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }

    }

}
