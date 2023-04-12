package kz.timka.today.example2.exception;

public class NegativeElementException extends RuntimeException {
    private int index;
    private int value;


    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    public NegativeElementException(int index, int value) {
        super("Был найдет отрицательный элемент " + value + " на позиции " + index);
        this.index = index;
        this.value = value;
    }
}
