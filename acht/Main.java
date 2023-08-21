package Klausur.acht;

public class Main {

    public static class Oben {
        public void gg (Oben o) { System.out.print("A "); }
        public void gg (Mitte m) { System.out.print("B "); }
    }
    public static class Mitte extends Oben {
        public void gg (Oben o) { System.out.print("L "); }
        public void gg (Unten u) { System.out.print("M "); }
    }
    public static class Unten extends Mitte {
        public void gg (Oben o) { System.out.print("X "); }
        public void gg (Mitte m) { System.out.print("Z "); }
    }
    public static class Prog {
        public static void test() {
            Oben oo = new Oben();
            Oben ff = new Mitte();
            Unten uu = new Unten();

            oo.gg(ff);
            oo.gg(uu);
            ff.gg(ff);
            ff.gg(uu);
            uu.gg(ff);
            uu.gg(uu);
        }
    }

    public static void main(String[] args) {
        Prog.test();
    }
}
