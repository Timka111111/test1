package kz.timka.today.example2;

public class Cat implements AutoCloseable{
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void close() throws Exception {

    }
}
