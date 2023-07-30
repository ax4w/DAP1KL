package Klausur.vierzen.Generischer_Suchbaum;

public class Stack<T>
{
    private DoublyLinkedList<T> elements;

    public Stack() {
        elements = new DoublyLinkedList<T>();
    }

    public void push( T o ) {
        elements.addFirst( o );
    }

    public T pop() {
        return elements.removeFirst();
    }

    public T peek() {
        return elements.getFirst();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

}