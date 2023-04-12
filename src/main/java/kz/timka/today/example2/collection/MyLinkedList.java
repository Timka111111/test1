package kz.timka.today.example2.collection;

public class MyLinkedList {
    private Node head;

    private int size;

    // 4, 5, 6
    public void add(int data) {
        if(head == null) {
            head = new Node(data);
            size++;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = new Node(data);
        size++;
    }
    // 4, 5, 6   10
    // 10, 4 , 5 , 6
    public void addFirst(int data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
        size++;
    }

    public void add(int index, int data) {
        if(index > size || index < 0) {
            throw new IndexOutOfBoundsException("Wrong");
        }

        if(index == 0) {
            addFirst(data);
        }
        // 4, 5 , 6, 7     (1, 9)
        Node node = new Node(data);
        Node leftNode = head;
        for (int i = 0; i < index - 1; i++) {
            leftNode = leftNode.next;
        }
        Node rightNode = leftNode.next;
        node.next = rightNode; // 9, 6 , 7
        leftNode.next = node;
        size++;
    }

    public void print() {
        Node current = head;
        while (current.next != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println(current.data);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add(10);
        myLinkedList.add(20);
        myLinkedList.add(30);
        myLinkedList.add(40);
        myLinkedList.print();
        myLinkedList.add(2, 60);
        myLinkedList.print();
        System.out.println(myLinkedList.size());
    }


}
