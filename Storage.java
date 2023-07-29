package Klausur;

public class Storage {

    private Ints[] values;

    public Storage(Ints[] v) {
        values = v;
    }

    public boolean contains(int p) {
        for(Ints cand: values) {
            if(cand == null) continue;
            if(cand.count(p) > 0) return true;
        }
        return false;
    }

    public boolean allAround(int p) {
        for(Ints cand: values) {
            if(cand == null || cand.count(p) == 0) return false;
        }
        return true;
    }
}
