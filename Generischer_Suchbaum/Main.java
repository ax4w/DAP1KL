package Klausur.Generischer_Suchbaum;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> n = new BinarySearchTree<>();
        n.add(50); // 0
        n.add(40);
        n.add(60);
        n.add(70);
        System.out.println(n.leavesAtLevel(1));

    }
}
