package Klausur.Generischer_Suchbaum;

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

    public T getContent()
    {
        if (!isEmpty() )
        {
            return content;
        } else {
            throw new RuntimeException();
        }
    }

    public T largestOn(int level) {
        if(!isEmpty() && level >= 0) {
            if(level == 0) {
                return this.getContent();
            }else{
                T r = this.rightChild.largestOn(level-1);
                T l = this.leftChild.largestOn(level-1);
                if(r == null && l == null) return null;
                else if(r == null) return l;
                else if(l == null) return r;
                if(r.compareTo(l) > 0) {
                    return r;
                }
                return l;
            }
        }else{
            return null;
        }
    }

    public BinarySearchTree<T> subTree(T o) {
        if(!this.isEmpty()) {
            if(this.content.compareTo(o) > 0) {
                return this.leftChild.subTree(o);
            }else if(this.content.compareTo(o) == 0){
                BinarySearchTree<T> r = this;
                this.leftChild = new BinarySearchTree<>();
                this.rightChild = new BinarySearchTree<>();
                this.content = null;
                return r;
            }else{
                return this.rightChild.subTree(o);
            }
        }else{
            return null;
        }
    }

    public boolean smallerExists(T obj) {
        if(!this.isEmpty()) {
            if(this.isLeaf()) {
                return obj.compareTo(this.getContent()) > 0;
            }
            return this.leftChild.smallerExists(obj);
        }else{
            return false;
        }
    }

    public int countNodes(int level) {
        if(!this.isEmpty()) {
            if(level % 2 != 0) {
                if(!this.isLeaf()) {
                    return 1 + this.leftChild.countNodes(level+1) + this.rightChild.countNodes(level+1);
                }else{
                    return 0;
                }
            }else{
                return this.leftChild.countNodes(level+1) + this.rightChild.countNodes(level+1);
            }
        }else{
            return 0;
        }
    }

    public int sortedUpTo(int n) {
        if(n > 0 && !isEmpty()) {
            n = leftChild.sortedUpTo(n);
            if(n <= 0) return 0;
            System.out.println( content );
            n = rightChild.sortedUpTo(n-1);
            if(n <= 0) return 0;
            return n;
        }else{
            return n;
        }
    }

    public int countNodes(int top, int bottom) {
        if(top <= bottom && !isEmpty()) {
            if(top != 0) {
                int l = this.rightChild.countNodes(top-1,bottom-1);
                int r = this.leftChild.countNodes(top-1,bottom-1);
                return l + r;
            }else {
                int l  = this.rightChild.countNodes(top,bottom-1);
                int r = this.leftChild.countNodes(top,bottom-1);
                return this.isLeaf() ? l + r : 1 + l + r;
            }
        }else{
            return 0;
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
