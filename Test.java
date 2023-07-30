package Klausur;

public class Test {

    public static class All{}
    public static class Most extends All{}
    public static class Normal extends Most {}
    public static  class Special extends Normal {}
    public static class Top
    {
        public void m (All p) {System.out.print("A");}
        public void m (Special p) {System.out.print("B");}
    }
    public static class Upper extends Top
    {
        public void m (Most p) {System.out.print("K");}
        public void m (Normal p) {System.out.print("L");}
    }
    public static class Middle extends Upper
    {
        public void m (Normal p) {System.out.print("P");}
    }
    public static class Bottom extends Middle
    {
        public void m (Most p) {System.out.print("X");}
        public void m (Special p) {System.out.print("Z");}
    }
    public static class Hi {
        public static void run() {
            All all = new Most();
            Most most = new Most();
            Normal normal = new Special();
            Special special = new Special();
            Top tu = new Upper();
            Top tm = new Middle();
            Middle mm = new Middle();
            Middle mb = new Bottom();
            tu.m(most);
            tu.m(special);
            tm.m(normal);
            tm.m(special);
            mm.m(normal);
            mm.m(special);
            mb.m(most);
            mb.m(normal);
            // A B A B P B X P
        }
    }
    public static void main(String[] args) {
        Hi.run();

    }
}
