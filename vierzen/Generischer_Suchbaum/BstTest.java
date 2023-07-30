package Klausur.vierzen.Generischer_Suchbaum;

public class BstTest
{
    public static void main( String[] args )
    {
        test();
    }

    public static void test()
    {
        BinarySearchTree<Student> students = new BinarySearchTree<>();
        students.add( new Student( "B", "Inf", 18 ) );
        students.add( new Student( "C", "M", 14 ) );
        students.add( new Student( "A", "Inf", 22 ) );
        students.add( new Student( "E", "M", 7 ) );
        students.add( new Student( "E", "M", 7 ) );
        students.show();
        System.out.println( "size: " + students.size() );
        students.add( new Student( "F", "Inf", 17 ) );
        students.add( new Student( "G", "M", 20 ) );
        students.show();
        System.out.println( "size: " + students.size() );
        System.out.println( "contains(..B..): " + students.contains( new Student( "B", "Inf", 18 ) ) );
        System.out.println( "contains(..G..): " + students.contains( new Student( "G", "M", 20 ) ) );
        System.out.println( "contains(..X..): " + students.contains( new Student( "X", "M", 51 ) ) );
        
        System.out.println("Iterator-Test mit Listen-Iterator");
        Iterator<Student> it = students.listBasedIterator();
        while ( it.hasNext() )
        {
            System.out.println( it.next() );
        }
        System.out.println("\nIterator-Test mit StackBasedTreeIterator");
        it = students.iterator();
        while ( it.hasNext() )
        {
            System.out.println( it.next() );
        }
    }
}
