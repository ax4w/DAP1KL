package Klausur.dreiundzwanzig;

import Klausur.Iterable;
import Klausur.Iterator;

public class Main {

    /*
    add(int[] arr1, int[]arr2), wenn arr1.length == arr2.length dann liste zurückgeben, die an index i die summe von
    arr1[i] und arr2[i] beinhaltet. sonst leere liste zurückgeben

     */
    public static int[] add(int[] arr1, int[] arr2) {
        if(arr1.length == arr2.length) {
            int[] r = new int[arr1.length];
            for(int i = 0; i < arr1.length; i++) {
                r[i] = arr1[i] + arr2[i];
            }
            return r;
        }else{
            return new int[0];
        }
    }

    public static int countSequence(int[] arr) {
        int last = arr[0];
        int sum = 0;
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] < last || arr[i] == last) {
                sum++;
                arr[i] = last;
            }else{
                arr[i] = last;
            }
        }
        sum++;
        return sum;
    }


    public Iterator<Integer> gIter(Iterable<Integer> p1, Iterable<Integer> p2) {
        return new Iterator<Integer>() {
            int dim = 0;
            Iterator<Integer> it = p1.iterator();
            
            @Override
            public boolean hasNext() {
                if(!it.hasNext() && dim == 0) {
                    dim++;
                    it = p2.iterator();
                }
                return it.hasNext();
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    return it.next();
                }else{
                    throw new IllegalStateException();
                }
            }
        };
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,1,2,3,1};
        System.out.println(countSequence(a));
    }
}
