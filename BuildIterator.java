package Klausur;

import java.util.*;
public class BuildIterator<T> implements Iterable<T>
{
    private Iterable<T> part1, part2;

    public BuildIterator( Iterable<T> p1, Iterable<T> p2 )
    {
        part1 = p1;
        part2 = p2;
    }

    public Iterator<T> iterator()
    {
        return new AllPartsIterator();
    }

    /* Aufgabe:
     * Vervollstaendigen Sie die Klasse  AllPartsIterator  derart,
     * dass der Iterator zuerst jeden der Inhalte  aus  part1  und anschliessend
     * jeden der Inhalte aus  part2  zurueckgibt. Falls keine weiteren Inhalte
     * vorhanden sind, soll  next  den Wert  null  zurueckgeben.
     */


    private class AllPartsIterator implements Iterator<T>
    {
        Iterator<T> it;
        boolean inScnd;

        public AllPartsIterator() {
            it = part1.iterator();
            inScnd = false;
        }

        @Override
        public boolean hasNext() {
            if(!it.hasNext() && !inScnd) {
                it = part2.iterator();
                inScnd = true;
            }
            return it.hasNext();
        }

        @Override
        public T next() {
            if(hasNext()) {
                return it.next();
            }
            return null;
        }
    }

}