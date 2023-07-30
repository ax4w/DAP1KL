package Klausur.vierzen;

import Klausur.Iterable;
import Klausur.Iterator;

public class IntSequence implements Iterable<Integer> {
    private int[] numbers;
    public IntSequence(int[] n) {
        numbers = n;
    }


    @Override
    public Iterator<Integer> iterator() {
        return new IntsIterator();
    }
    private class IntsIterator implements Iterator<Integer> {

        private int nextPosition;

        public IntsIterator() {
            nextPosition = 0;
        }
        @Override
        public boolean hasNext() {
            return nextPosition < numbers.length;
        }

        @Override
        public Integer next() {
            if(hasNext()) {
                int a = numbers[nextPosition];
                nextPosition++;
                return a;
            }else{
                throw new RuntimeException();
            }
        }
    }

}
