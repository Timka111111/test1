package kz.timka.today.example2;

public class SimpleBox implements Comparable<Cat> {
    private Object obj;

    public SimpleBox(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }


    @Override
    public int compareTo(Cat o) {
        return 0;
    }
}
