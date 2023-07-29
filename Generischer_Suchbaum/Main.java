package Klausur.Generischer_Suchbaum;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> n = new BinarySearchTree<>();
        n.add(50); // 0
        n.add(40); // 1
        n.add(30); // 2
        n.add(20); // 3
        n.add(10); // 4
        n.add(60); // 1
        n.add(70); // 2
        n.add(80); // 3
        n.add(90); // 4
        System.out.println(n.countNodes(0, 4));
    }
}
