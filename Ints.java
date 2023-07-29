package Klausur;

public class Ints {

    private int[] numbers;

    public Ints(int[] n) {
        numbers = n;
    }

    public void set(int index, int val) {
        if(index < 0 || index >= numbers.length) return;
        numbers[index] = val;
    }
    public int count(int val) {
        int quanity = 0;
        for(int cand: numbers) {
            if(cand == val) quanity++;
        }
        return quanity;
    }

    public void substitute(int oldVal, int newVal) {
        for(int i = 0; i < numbers.length; i++) {
            if(numbers[i] == oldVal) numbers[i] = newVal;
        }
    }
}
