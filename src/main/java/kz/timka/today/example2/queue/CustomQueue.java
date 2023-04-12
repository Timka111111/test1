package kz.timka.today.example2.queue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;

public class CustomQueue extends AbstractQueue<String> {

    private LinkedList<String> elements;

    public CustomQueue(LinkedList<String> elements) {
        this.elements = new LinkedList<>();
    }

    @Override
    public Iterator<String> iterator() {
        return elements.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean offer(String s) {
        return elements.add(s);
    }

    @Override
    public String poll() {
        return elements.removeFirst();
    }

    @Override
    public String peek() {
        return elements.getFirst();
    }

    public static void main(String[] args) {
        CustomQueue customQueue = new CustomQueue(new LinkedList<>());

        customQueue.offer("A");
        customQueue.offer("B");
        customQueue.offer("C");
        System.out.println(customQueue);
        System.out.println(customQueue.poll());
        System.out.println(customQueue);
        System.out.println(customQueue.peek());
        System.out.println(customQueue);
    }
}


