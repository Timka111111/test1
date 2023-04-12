package kz.timka.today.example2.enums;

public enum SummerMonth {
    JUNE(6), JULY(7), AUGUST(8);

    private int index;


    SummerMonth(int index) {
        this.index = index;
    }

    public void description() {
        System.out.println("this is summer months");
    }
    public int getIndex() {
        return index;
    }
}
