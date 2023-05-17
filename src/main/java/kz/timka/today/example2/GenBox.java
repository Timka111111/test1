package kz.timka.today.example2;

public class GenBox<T> {
    private T obj;

    public GenBox(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
