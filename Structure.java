package Klausur;


import java.util.Iterator;

public class Structure<T> implements Iterable<T> {

    private Iterable<T> values;

    public Structure(Iterable<T> val) {
        values = val;
    }

    public Iterator<T> iterator() {
        return new StructIter();
    }

    private class StructIter implements Iterator<T> {

        Iterator<T> it;

        public StructIter() {
            it = values.iterator();
        }

        public boolean hasNext() {
            return it.hasNext();
        }

        public T next() {
            if(it.hasNext()) {
                return it.next();
            }
            return null;
        }
    }
}
