package Klausur.neuzen_zwei;

import Klausur.neuzen_zwei.Generische_Liste.DoublyLinkedList;
import Klausur.neuzen_zwei.Generischer_Suchbaum.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {

    //Aufgabe 2 (Polymorphie)
    // A B X B X B E X

    public static  class All {  }
    public static  class Most extends All {  }
    public static class Normal extends Most {  }
    public static  class Special extends Normal {  }
    public static class Top
    {
        public void m( All p ) { System.out.print("A"); }
        public void m( Special p ) { System.out.print("B"); }
    }
    public static class Upper extends Top
    {
        public void m( Most p ) { System.out.print("M"); }
        public void m( Normal p ) { System.out.print("P"); }
    }
    public static class Middle extends Upper
    {
        public void m( Normal p ) { System.out.print("X"); }
    }
    public static class Bottom extends Middle
    {
        public void m( All p ) { System.out.print("E"); }
    }
    public static class Test
    {
        public static void run()
        {
            All all = new Most();
            Most most = new Most();
            Normal normal = new Special();
            Special special = new Special();
            Top tu = new Upper();
            Upper um = new Middle();
            Middle mm = new Middle();
            Middle mb = new Bottom();
            tu.m( most );
            tu.m( special );
            um.m( normal );
            um.m( special );
            mm.m( normal );
            mm.m( special );
            mb.m( all );
            mb.m( normal );
        }
    }

    // Aufgabe 3 (Methoden)
    /*
    Die Methode top2 gibt ein neues Feld zurück, das die beiden größten in arr vorkommenden Werte
    enthält. Sie können dabei davon ausgehen, dass in arr kein Wert doppelt vorkommt und dass alle Werte
    in arr größer als 0 sind. Hat arr weniger als zwei Elemente, wird ein Feld der Länge 0 zurückgegeben.
    Das Feld arr soll unverändert bleiben.
     */
    public static int[] top2(int[] arr) {
        int[] r = new int[2];
        r[0] = arr[0];
        r[1] = arr[1];
        if(arr[0] < arr[1]) {
            r[0] = arr[1];
            r[1] = arr[0];
        }
        for(int i: arr) {
            if(i > r[0]) {
                r[1] = r[0];
                r[0] = i;
            }else if(i > r[1] && i < r[0]) {
                r[1] = i;
            }
        }
        return r;
    }

    /*
    Die Methode nTimes gibt true zurück, falls es mindestens einen Wert im Feld arr gibt, von dem es
    genau n Vorkommen gibt.
    Existiert kein solcher Wert oder ist n kleiner als 1, wird false zurückgegeben.
    Das Feld arr soll unverändert bleiben.
     */

    public static boolean nTimes(int[] arr ,int n) {
        for(int i = 0; i < arr.length; i++) {
            int c = 1;
            for(int j = i+1; j < arr.length; j++) {
                if(arr[j] == arr[i]) c++;
            }
            if(c == n) return true;
        }
        return false;
    }

    /*
    Vervollständigen Sie die rekursive Methode boolean do(int[] arr1, int[] arr2, int p),
    die von der Methode use aufgerufen wird.
    Die Methode use gibt true zurück, falls das Feld arr1 an allen Indizes die gleichen Werte wie das Feld
    arr2 hat. Ist das Feld arr2 kürzer als das Feld arr1 oder unterscheiden sich Werte am gleichen Index,
    wird false zurückgegeben.
     */

    public static boolean use( int[] arr1, int[] arr2 )
    {
        return doIt( arr1, arr2, arr1.length-1 );
    }
    private static boolean doIt( int[] arr1, int[] arr2, int p ) {
        if(p < 0) return true;
        if(arr1.length != arr2.length) return false;
        if(arr1[p] != arr2[p]) return false;
        return doIt(arr1, arr2, p - 1);
    }

    /*
    Implementieren Sie den Iterator LimitedIterator<T> für die Klasse Container<T> .
    Der Iterator LimitedIterator liefert nacheinander bis zu 100 Inhalte des Attributs data. Besitzt data
    weniger als 100 Inhalte, so liefert der Iterator alle Inhalte.
    Falls kein Inhalt zurückgegeben kann, gibt die Methode next() den Wert null zurück.
     */

    public static class Container<T> implements java.lang.Iterable<T> {

        private java.lang.Iterable<T> data;
        public Container( java.lang.Iterable<T> d ) {
            data = d;
        }
        public java.util.Iterator<T> iterator() {
            return new LimitedIterator();
        }

        private class LimitedIterator implements java.util.Iterator<T> {

            int limit;
            java.util.Iterator<T> it;

            public LimitedIterator() {
                limit = 10;
                it = data.iterator();
            }

            @Override
            public boolean hasNext() {
                return limit > 0 && it.hasNext();
            }

            @Override
            public T next() {
                if(hasNext()) {
                    limit--;
                    return it.next();
                }
                return null;
            }
        }
    }

    /*
    Die Methode <X> boolean containsDouble( Iterable<X> data ) gibt true zurück, falls der Iterator
    von data mindestens einmal zwei Inhalte liefert, die gleich sind. Diese Inhalte müssen nicht unmittelbar
    aufeinander folgen. Der Vergleich der Inhalte soll mit der Methode equals erfolgen. Gibt es keine zwei
    gleichen Inhalte, wird false zurückgegeben.
    Sie dürfen bei der Implementierung davon ausgehen, dass der Parameter data nicht auf null verweist
    und auch nur Inhalte besitzt, die ungleich null sind.
     */

    public static <X> boolean containsDouble( java.lang.Iterable<X> data ) {
        java.util.Iterator<X> it1 = data.iterator();
        while (it1.hasNext()) {
            X val = it1.next();
            java.util.Iterator<X> it2 = data.iterator();
            while (it2.hasNext() && it2.next() != val);
            if(it2.hasNext() && it2.next() == val) return true;
            while (it2.hasNext()) {
                if(it2.next() == val) return true;
            }
        }
        return false;
    }

    //lambda
    public interface IntFunc {
        int apply( int x);
    }
    public interface IntIntFunc {
        int apply( int x, int y );
    }

    public static class Data {
        private int[] iValues;
        public Data( int[] p ) { iValues = p; }
        public int act( IntFunc f, IntIntFunc g ) {
            int result = 0;
            int i = f.apply( -1 );
            while ( i >= 0 && i < iValues.length ) {
                result = g.apply( iValues[i], result );
                i = f.apply( i );
            }
            return result;
        }
    }

    public static void test(Data d) {
        System.out.println(d.act(x -> {
            int r = x + 1;
            if ((r) % 2 == 0) r++;
            return r;
        }, (x, y) -> {
            return x + y;
        }));

        System.out.println(d.act(x -> x+1, (x, y) -> {
            if (x >= 0) return y+1;
            return y;
        }));

        System.out.println(d.act(x -> x+1, (x, y) -> x));

        System.out.println(d.act(x -> {
            if (x == 10) return -1;
            return x+1;
        }, (x, y) -> x + y));
    }


    public static void main(String[] args) {
        int[] test = new int[]{1,2,3,4,6,1,2,3,4,7};
        int[] test2 = new int[]{1,2,3,4,6,1,2,3,4,8};
        System.out.println(Arrays.toString(top2(test)));
        System.out.println(nTimes(test,1));
        System.out.println(use(test, test2));



        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(6);
        a.add(7);
        a.add(8);
        a.add(8);
        Container<Integer> c = new Container<>(a);

        System.out.println("container");
        Iterator<Integer> ci = c.iterator();
        while (ci.hasNext()) System.out.println(ci.next());

        System.out.println("contains double");
        System.out.println(containsDouble(a));

        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        dll.add(1);
        dll.add(2);
        dll.add(3);
        dll.add(4);
        DoublyLinkedList<Integer> dll2 = new DoublyLinkedList<>();
        dll2.add(5);
        dll2.add(6);
        dll2.add(7);
        dll2.add(8);
        dll.insert(dll2,4);
        dll.showAll();

        DoublyLinkedList<Integer> dll3 = new DoublyLinkedList<>();
        dll3.add(null);
        dll3.add(6);
        dll3.add(null);
        dll3.add(null);
        System.out.println("---");
        System.out.println(Arrays.toString(dll3.positions()));
        System.out.println("---");

        BinarySearchTree<Integer> b = new BinarySearchTree<>();

        b.add(50);
        b.add(51);
        b.add(52);
        b.add(53);
        b.add(54);
        b.add(49);
        b.add(48);
        b.add(47);
        b.add(46);
        b.add(45);

        System.out.println(b.bigOnLevel(5));

        Data d = new Data(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,-1});
        //                            ^   ^   ^   ^    ^    ^     = 42
        //                          ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^     = 12
        //                                                      ^ = -1
        //                          ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^         = 66
        test(d);

    }
}
