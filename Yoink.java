package Klausur;

public class Yoink {



    public static class All {}
    public static class Most extends All {}
    public static class Normal extends Most  {}
    public static class Special extends Normal {}

    public static class Top {
        public void m(All p) {
            System.out.printf("Z");
        }
        public void m(Normal p) {
            System.out.printf("T");
        }
    }
    public static class Upper extends Top{
        public void m(Most p) {
            System.out.printf("L");
        }
    }
    public static class Middle extends Upper {
        public void m(All p) {
            System.out.printf("H");
        }
        public void m(Most p) {
            System.out.printf("E");
        }
        public void m(Special p) {
            System.out.printf("Y");
        }
    }
    public static class Bottom extends Middle {
        public void m(Normal p) {
            System.out.printf("X");
        }
    }

    public static void main(String[] args) {
        All ref1 = new Most();
        Most ref2 = new Normal();
        Normal ref3 = new Special();

        Top tu = new Upper();
        Upper uu = new Upper();
        Upper um = new Middle();
        Middle mb = new Bottom();

        tu.m(ref1);
        tu.m(ref2);
        uu.m(ref2);
        uu.m(ref3);
        um.m(ref2);
        um.m(ref3);
        mb.m(ref1);
        mb.m(ref3);
    }

}
