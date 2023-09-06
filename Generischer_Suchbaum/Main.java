package Klausur.Generischer_Suchbaum;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> n = new BinarySearchTree<>();
        n.add(50); // 0
        n.add(40);
        n.add(60);
        n.add(70);
        System.out.println("n show before:");
        n.show();
        BinarySearchTree<Integer> k = n.subTree(60);
        System.out.println("k show");
        k.show();
        System.out.println("n show after");
        n.show();
        //System.out.println(n.leavesAtLevel(1));

    }
}
