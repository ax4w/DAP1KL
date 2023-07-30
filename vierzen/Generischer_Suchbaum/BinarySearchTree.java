package Klausur.vierzen.Generischer_Suchbaum;

public class BinarySearchTree<T extends java.lang.Comparable<T>>
{
    private T content;
    private BinarySearchTree<T> leftChild, rightChild;

    public BinarySearchTree() 
    {
        content = null;
        leftChild = null;
        rightChild = null;
    }

    public void showDecreasing() {
        if(isEmpty()) return;
        rightChild.showDecreasing();
        System.out.println(content);
        leftChild.showDecreasing();
    }

    public int nodesAtLevel(int n) {
        if(n < 0 || isEmpty()) return 0;
        else{
            if(n == 0) {
                if(isLeaf()) return 0;
                return 1;
            }
            return rightChild.nodesAtLevel(n-1) + leftChild.nodesAtLevel(n-1);
        }
    }

    public int sizeIfBalanced() {
        if(isEmpty()) return 0;
        else{
            if(isLeaf()) return 0;
            int a = rightChild.sizeIfBalanced();
            int b = leftChild.sizeIfBalanced();
            if(a == -1 || b == -1) return -1;
            if(Math.abs(a-b) > 1) return -1;
            return 1+a+b;

        }
    }

    public T getContent()
    {
        if (!isEmpty() )
        {
            return content;
        } else {
            throw new RuntimeException();
        }
    }

    public boolean isEmpty() 
    {
        return content == null;
    }

    public boolean isLeaf() 
    {
        return !isEmpty() && leftChild.isEmpty() && rightChild.isEmpty();
    }

    public void add( T t )
    {
        if ( isEmpty() ) 
        {
            content = t;
            leftChild = new BinarySearchTree<T>();
            rightChild = new BinarySearchTree<T>();
        }
        else
        {
            if ( content.compareTo( t ) > 0 )
            {
                leftChild.add( t );
            }
            else if ( content.compareTo( t ) < 0 )
            {
                rightChild.add( t );
            }
        }
    }

    public boolean contains( T t )
    {
        if ( isEmpty() ) 
        {
            return false;
        }
        else
        {
            if ( content.compareTo( t ) > 0 )
            {
                return leftChild.contains( t );
            }
            else if ( content.compareTo( t ) < 0 )
            {
                return rightChild.contains( t );
            } 
            return true;
        }
    }

    public int size() 
    {
        if ( isEmpty() ) 
        {
            return 0;
        }
        else
        {
            return 1 + leftChild.size() + rightChild.size();
        }       
    }

    public void show()
    {
        if ( !isEmpty() ) 
        {
            leftChild.show();
            System.out.println( content );
            rightChild.show();
        }
    } 

    public Iterator<T> listBasedIterator()
    {
        DoublyLinkedList<T> list = new DoublyLinkedList<T>();
        toList( list );
        return list.iterator();
    }

    private void toList( DoublyLinkedList<T> list )
    {
        if ( !isEmpty() ) 
        {
            leftChild.toList( list );
            list.add( content );
            rightChild.toList( list );
        }
    }

    private class StackBasedIterator extends Iterator<T>
    {
        private Stack<BinarySearchTree<T>> nodes;

        public StackBasedIterator()
        {
            nodes = new Stack<BinarySearchTree<T>>();
            descendLeftAndPush( BinarySearchTree.this );
        }

        public T next() 
        {
            if ( hasNext() ) {
                T content = nodes.peek().getContent();
                descendLeftAndPush( nodes.pop().rightChild ); 
                return content;
            } else {
                throw new IllegalStateException();
            }
        }

        public boolean hasNext()
        {
            return !nodes.isEmpty();
        } 

        private void descendLeftAndPush( BinarySearchTree<T> root )
        {
            BinarySearchTree<T> current = root;
            while ( !current.isEmpty() )
            {
                nodes.push( current );
                current = current.leftChild;
            }
        }

    }
    
    public Iterator<T> iterator()
    {
        return new StackBasedIterator();
    }
}
