package Klausur.vierzen;

public class Storage {

    private Ints[] values;
    public Storage(Ints[] n) {
        values = n;
    }

    public boolean contains(int p) {
        for(Ints cand: values){
            if(cand != null) {
                if(cand.count(p) > 0) return true;
            }
        }
        return false;
    }

    public boolean allAround(int p) {
        for(Ints cand: values) {
            if(cand == null) return false;
            if(cand.count(p) == 0) return false;
        }
        return true;
    }

}
