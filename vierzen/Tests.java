package Klausur.vierzen;

import Klausur.Iterator;
import Klausur.vierzen.Generische_Liste.DoublyLinkedList;
import Klausur.vierzen.Generischer_Suchbaum.BinarySearchTree;
import Klausur.vierzen.Generischer_Suchbaum.BstTest;

import java.util.Arrays;

public class Tests {


    public static void IntsTest() {
        Ints ints = new Ints(new int[]{1,2,3,4,5,6,7,8});
        System.out.println("Erwartet: 1, bekommen: " + ints.count(1));
        ints.set(1,1);
        System.out.println("Erwartet: 2, bekommen: " + ints.count(1));
        ints.substitute(7,1);
        System.out.println("Erwartet: 3, bekommen: " + ints.count(1));
    }

    public static void SotrageTest() {
        Storage storage = new Storage(new Ints[]{
                new Ints(new int[]{1,2,3,4}),
                new Ints(new int[]{4,5,6,7})
        });
        System.out.println("Erwartet: true, bekommen: " +  storage.contains(1));
        System.out.println("Erwartet: true, bekommen: " + storage.allAround(4));
    }

    public static void MethodenTest() {
        int[] test = new int[]{1,1,2,3,4,4,4,5,6,7};
        int[] test2 = new int[]{1,3};
        Object[] test3 = new Object[]{null,1,2,3,4,null,5,6,null,null,7,8,null};
        System.out.println("Erwartet: 3, bekommen: " + Methoden.countPairs(test));
        System.out.println("Erwartet [1,1,0,1,0,0,0,0,0,0], bekommen: " + Arrays.toString(Methoden.countInSecond(test, test2)));
        Methoden.compress(test3);
        System.out.println("Erwartet: 1,2,3,4,5,6,7,8,null,null,null,null,null bekommen:" + Arrays.toString(test3));
        int[] test4 = new int[]{-1,3};
        System.out.println("Erwartet: false, bekommen: " + Methoden.isPositiv(test4, test4.length - 1));
    }

    public static void IteratorTest() {
        IntSequence intSequence = new IntSequence(new int[]{1,2,3,4,5,6});
        IntSequence intSequence2 = new IntSequence(new int[]{6,7,8,9});
        Iterator<Integer> it = intSequence.iterator();
        while (it.hasNext()) System.out.println(it.next());
        System.out.println("Erwartet: true, bekommen: " + Main.oneInBoth(intSequence, intSequence2));
    }

    public static void DLLTest() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        dll.add(1);
        dll.add(2);
        dll.add(3);
        dll.add(2);
        dll.add(1);
        System.out.println("Erwartet: true, bekommen: " + dll.isPalindrome());
        dll.add(1);
        System.out.println("Erwartet 2, bekommen: " + dll.longestSequence());
        DoublyLinkedList<Integer> dll2 = dll.tail(3);
        dll2.showAll();
    }

    public static void BST() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(50); //0
        bst.add(40); // 1
        bst.add(41); // 2
        bst.add(30); // 3
        bst.add(42); // 3
        bst.add(60); // 1
        bst.add(59); // 2
        bst.add(70); // 3
        bst.add(57); // 3
        bst.showDecreasing();
        System.out.println("Erwartet: 1, bekommen: " + bst.nodesAtLevel(0));
        System.out.println("Erwartet: 5, bekommen: " + bst.sizeIfBalanced());
    }

    public static void main(String[] args) {
        System.out.println("Ints");
        IntsTest();
        System.out.println("Storage");
        SotrageTest();
        System.out.println("Methoden");
        MethodenTest();
        System.out.println("Iterator");
        IteratorTest();
        System.out.println("DLL");
        DLLTest();
        System.out.println("BST");
        BST();
    }

}
