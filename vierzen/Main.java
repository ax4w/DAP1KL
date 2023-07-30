package Klausur.vierzen;

import Klausur.vierzen.Generische_Liste.*;
import Klausur.Iterator;

public class Main {


    public static boolean oneInBoth(IntSequence p1, IntSequence p2) {
        Iterator<Integer> iter1 = p1.iterator();
        while (iter1.hasNext()) {
            int iValue = iter1.next();
            Iterator<Integer> iter2 = p2.iterator();
            while (iter2.hasNext()) {
                if(iValue == iter2.next()) return true;
            }
        }
        return false;
    }

    public static class RemoveNullStrat<S> extends DoublyLinkedList.DeletionStrategy<S> {
        @Override
        public boolean select(S ref) {
            return ref == null;
        }
    }

    public static class RemoveSecondStrat<S> extends DoublyLinkedList.DeletionStrategy<S> {
        public boolean remove;

        public RemoveSecondStrat() {
            remove = true;
        }
        @Override
        public boolean select(S ref) {
            if (remove) {
                remove = false;
                return true;
            }else{
                remove = true;
                return false;
            }
        }
    }

}
