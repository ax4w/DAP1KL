package Klausur;

public class Test21 {

    public static class Global {}
    public static class Country extends Global {}
    public static class State extends Country {}
    public static class City extends State {};

    public static class Alpha {
        public void m (Global g) {
            System.out.println("G");
        }
        public void m (City c) {
            System.out.println("c");
        }
    }
    public static class Beta extends Alpha {
        public void m (State s) {
            System.out.println("s");
        }
    }
    public static class Gamma extends Beta {
        public void m (Global g) {
            System.out.println("GG");
        }
    }

    public static class Delta extends Gamma {
        public void m (Country c) {
            System.out.println("Co");
        }
    }

    public static class Kek {
        public static void run() {
            Alpha ad = new Delta();
            Alpha ab = new Beta();
            Beta bg = new Gamma();
            Beta bb = new Beta();

            ad.m(new City());
            ad.m(new Country());
            bb.m(new Global());
            bb.m(new State());
            ab.m(new Country());
            bg.m(new City());
            // c GG G s G c
        }

    }

    public static void main(String[] args) {
        Kek.run();
    }

}
