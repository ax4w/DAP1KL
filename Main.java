package Klausur;

public class Main {


    public class Tuple {
        private String text;
        private int value;
        public Tuple( String t, int v ) { text = t; value = v; }
        public String getText() { return text; }
        public int getValue() { return value; }
        public void setValue( int v ) { value = v; }
    }


    public static class Group
    {
        private Tuple[] map;
        public Group( int n ) {
            map = new Tuple[n];
        }
        public int size() {
            return map.length;
        }
        public Tuple delete( int i ) {
            if(i < 0 || i >= map.length) return null;
            Tuple p = map[i];
            map[i] = null;
            return p;
        }

        public Group copy() {
            Group p = new Group(map.length);
            for(Tuple t : map) {
                p.insert(t);
            }
            return p;
        }
        public boolean insert(Tuple p) {
            if(p == null) return false;
            for(int i = 0; i < map.length; i++) {
                if(map[i] == null) {
                    map[i] = p;
                    return true;
                }
            }
            return false;
        }
    }

    public static class Use {
        public static Group extract(Group g, int v) {
           Group g1 = g.copy();
           for(int i = 0; i< g1.size(); i++) {
               Tuple t = g1.delete(i);
               if(t != null && t.getValue() < v) {
                   g1.insert(t);
               }
           }
           return g1;
        }

        public  static int maxValue(Group s) {
            Group g1 = s.copy();
            int m = g1.delete(0).value;
            for(int i = 1; i < s.size(); i++) {
                Tuple t = s.delete(i);
                if(t != null && t.getValue() > m) m = t.getValue();
            }
            return m;
        }
    }

    public static class Numbers {
        private int[] values;
        public Numbers(int n) {
            values = new int[n];
        }

        public void reset(int index) {
            if(index < 0 || index >= values.length) return;
            values[index] = 0;
        }

        public boolean insert(int val) {
            for(int i = 0; i < values.length; i++) {
                if(values[i] == 0) {
                    values[i] = val;
                    return true;
                }
            }
            return false;
        }

        public Numbers copy() {
            Numbers n =  new Numbers(values.length);
            for(int i: values) {
                n.insert(i);
            }
            return n;
        }
    }

    static class Top
    {
        public void m( Middle p ) { System.out.print("A "); }
    }
    static class Middle extends Top
    {
        public void m( Object p ) { System.out.print("M "); }
        public void m( Middle p ) { System.out.print("L "); }
    }
    static class Bottom extends Middle
    {
        public void m( Object p ) { System.out.print("V "); }
        public void m( Middle p ) { System.out.print("X "); }
    }
    static class Test
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

    public static boolean notIn(int[] arr, Iterable<Integer> p) {
        for(int fromArr: arr) {
            Iterator<Integer> it = p.iterator();
            while (it.hasNext()) {
                if(it.next() == fromArr) return false;
            }
        }
        return true;
    }

    public interface IntegerBiFunction {
        Integer apply(Integer p1, Integer p2);
    }

    public static class IData {
        private Integer[] iValues;
        public IData(Integer[] p) {
            iValues = p;
        }
        public Integer doIt(IntegerBiFunction ibf) {
            Integer lastApply = null;
            for(Integer value: iValues) {
                if(value != null) {
                    lastApply = ibf.apply(value,lastApply);
                }
            }
            return lastApply;
        }
    }

    public static void IDataLambda(IData d) {
        System.out.println(d.doIt((x, y) -> {
            if (x != null) {
                return y == null ? 1 : y + 1;
            }
            return null;
        }));

        System.out.println(d.doIt((x, y) -> x));

        System.out.println(d.doIt((x, y) -> {
            if(y == null) return x;
            if(x > y) return x;
            return y;
        }));
    }


    public static void main(String[] args) {
        IntNumbers intNumbers = new IntNumbers(new int[]{1,2,3,4,5}, new int[]{6,7,8,9,0});
        Iterator<Integer> it = intNumbers.iterator();
        //while (it.hasNext()) System.out.println(it.next());

        IData iData = new IData(new Integer[]{1,2,26,4,5,6,7,10});
        IDataLambda(iData);
    }

}
