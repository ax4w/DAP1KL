package Klausur.vierzen;

public class Polymorphie {

    public static void m( double p ) { System.out.print( "A " ); }
    public static void m( int p ) { System.out.print( "L " ); }
    public static void m( char p ) { System.out.print( "X " ); }
    public static void run()
    {
        m( 'a' );
        m( 7 / 3 );
        m( 7 / 3.0 );
        m( 5.0 );
        m( 'a' / 'b' );
        m( 5 );
    }

    static class  Top
    {
        public void m( Middle p ) { System.out.print("A "); }
    }
    static class  Middle extends Top
    {
        public void m( Object p ) { System.out.print("M "); }
        public void m( Middle p ) { System.out.print("L "); }
    }
    static class Bottom extends Middle
    {
        public void m( Object p ) { System.out.print("V "); }
        public void m( Middle p ) { System.out.print("X "); }
    }
    class Test
    {
        public static void run()
        {
            Top tm = new Middle();
            Middle mb = new Bottom();
            tm.m( mb );
            tm.m( new Bottom() );
            mb.m( mb );
            mb.m( tm );
            mb.m( new Middle() );
            new Bottom().m( tm );
        }
    }

    public static void main(String[] args) {
        run();
        System.out.println("");
        Test.run();
    }
}
