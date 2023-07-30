package Klausur;

public class Strcutres<T> implements Iterable<T> {
    private T[]s1,s2;

    public Strcutres(T[] a, T[]b) {
        s1 = a;
        s2 = b;
    }
    @Override
    public Iterator<T> iterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<T> {
        int index;
        T[] l;

        public ReverseIterator() {
            index = s2.length-1;
            l = s2;
        }

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public T next() {
            if(hasNext()) {
                T elm = l[index];
                index--;
                if(index < 0 && l != s1) {
                    l = s1;
                    index = s1.length-1;
                }
                return elm;
            }
            return null;
        }
    }
}
