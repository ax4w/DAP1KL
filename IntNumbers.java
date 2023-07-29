package Klausur;

/*
    Aus der Klausur: 2017
 */
public class IntNumbers implements Iterable<Integer> {

    public int[] numbers1, numbers2;

    public IntNumbers(int[] numbers1, int[] numbers2) {
        this.numbers1 = numbers1;
        this.numbers2 = numbers2;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IntNumbersIterator();
    }

    private class IntNumbersIterator implements Iterator<Integer> {

        int[] l;
        int index;
        boolean inScnd;

        public IntNumbersIterator() {
            l = numbers1;
            index = 0;
            inScnd = false;
        }

        @Override
        public boolean hasNext() {
            if(index >= l.length && !inScnd) {
                l = numbers2;
                index = 0;
                inScnd = true;
            }
            return index < l.length;
        }

        @Override
        public Integer next() {
            if(hasNext()) {
                return l[index++];
            }
            return null;
        }
    }
}
