package Klausur.Generische_Liste;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        dll.add(1);
        dll.add(2);
        dll.add(3);
        dll.add(4);
        dll.add(9);
        System.out.println(dll.splitBehind(9));
        dll.showAll();
        /*System.out.println(dll.splitBehind(3));
        dll.showAll();
        DoublyLinkedList<Integer> dll2 = new DoublyLinkedList<>();
        dll2.add(5);
        dll2.add(6);
        dll2.add(7);
        dll2.add(8);
        dll.showAll();
        System.out.println(Arrays.toString(dll.positions()));
        //dll.appendFirst();
        dll.inject(dll2,3);
        dll.showAll();
        dll.moveToHead(3);
        //dll.splitBehind(2);
        dll.showAll();
        System.out.println(dll2.splitBehind(10));
        DoublyLinkedList<Integer> dll3 = new DoublyLinkedList<>();
        dll3.add(null);
        dll3.add(5);
        dll3.add(6);
        dll3.add(null);
        dll3.add(7);
        dll3.add(8);
        dll3.add(null);
        System.out.println(dll3.strip());
        dll3.showAll();
        System.out.println(dll3.allIn(new Integer[]{5, 6, 7, 8,9}));*/
    }
}
