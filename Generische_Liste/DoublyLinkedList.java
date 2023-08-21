package Klausur.Generische_Liste;

public class DoublyLinkedList<T>
{
    private Element first, last;
    private int size;

    public DoublyLinkedList()
    {
        first = last = null;
        size = 0;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void add( T content ) 
    {
        Element e = new Element( content );
        if ( isEmpty() ) 
        {
            first = last = e;
        }
        else 
        {
            last.connectAsSucc( e );
            last = e;
        }
        size++;
    }

    public void addFirst( T content ) 
    {
        Element e = new Element( content );
        if ( isEmpty() ) 
        {
            first = last = e;
        }
        else 
        {
            first.connectAsPred( e );
            first = e;
        }
        size++;
    }

    public T getFirst() 
    {
        if ( !isEmpty() )
        {
            return first.getContent();
        }
        else
        {
            throw new IllegalStateException();
        }
    }

    public T get( int index ) 
    {
        if ( index >= 0 && index < size )
        {
            Element current = first;
            for ( int i = 0; i < index; i++ )
            {
                current = current.getSucc();
            }
            return current.getContent();
        }
        else
        {
            throw new IllegalStateException();
        }
    }

    public T removeFirst()
    {
        if ( !isEmpty() ) 
        {
            T result = first.getContent();
            if ( first.hasSucc() )
            {
                first = first.getSucc();
                first.disconnectPred();
            }
            else
            {
                first = last = null;
            }
            size--;
            return result;
        }
        else
        {
            throw new IllegalStateException();
        }
    }

    public void showAll()
    {
        Element current = first;
        while ( current != null )
        {
            if(current.getContent() == null) {
                System.out.print("null");
            }else{
                System.out.print( current.getContent().toString() );
            }
            System.out.print(" ");
            current = current.getSucc();
        }
        System.out.println();
    }
    

    // Iterator

    public Iterator<T> iterator()
    {
        return new ForwardIterator();
    }

    private abstract class ListIterator extends Iterator<T>
    {
        Element current;

        public boolean hasNext()
        {
            return current != null;
        }

        public T next()
        {
            if ( hasNext() )
            {
                T content = current.getContent();
                current = step();
                return content;
            }
            else
            {
                throw new IllegalStateException();
            }
        }

        abstract Element step();

    }

    private class ForwardIterator extends ListIterator
    {
        public ForwardIterator()
        {
            current = first;
        }
        
        Element step()
        {
            return current.getSucc();
        }
    }

    
    // strategies

    public static abstract class SubstitutionStrategy<E>
    {
        public abstract E substitute( E ref );
    }

    public void substituteAll( SubstitutionStrategy<T> s )
    {
        Element current = first;
        while ( current != null )
        {
            current.setContent( s.substitute( current.getContent() ) );
            current = current.getSucc();
        }
    }

    public static abstract class InspectionStrategy<E>
    {
        public abstract void inspect( E ref );
    }

    public void inspectAll( InspectionStrategy<T> s )
    {
        Element current = first;
        while ( current != null )
        {
            s.inspect( current.getContent() );
            current = current.getSucc();
        }
    }

    public void inject(DoublyLinkedList<T> into, int p) {
        Element current = first;
        if(this.size > p) {
            for(int i = 0; i < p; i++) {
                current = current.getSucc();
            }
            Element a = current.getSucc();
            current.disconnectSucc();
            current.connectAsSucc(into.first);
            current.getSucc().connectAsPred(current);
            while (current.getSucc() != null) current = current.getSucc();
            this.size += into.size;
            current.connectAsSucc(a);
            current.getSucc().connectAsPred(current);
            into.first = new Element(null);
        }
    }

    public void moveToTail(T obj) {
        // 1 2 3 4 5 6 7
        //       ^
        // 5674
        Element f = first;
        int s = 0;
        while (f != null) {
            if(f.getContent().equals(obj)) {
                f.disconnectPred();
                first = f.getSucc();
                first.disconnectPred();
                f.disconnectSucc();
                last.connectAsSucc(f);
                last.getSucc().connectAsPred(last);
                last = last.getSucc();
                size -= s;
                return;
            }
            s++;
            f = f.getSucc();
        }
    }

    public void moveToHead(int n) {
        if(n > 0 && n < this.size) {
            Element c = first;
            for(int i = 0; i < this.size-n; i++) {
                c = c.getSucc();
            }
            Element e = c.getPred();
            Element f = c;
            c.disconnectPred();
            last = e;
            while (c.getSucc() != null) c = c.getSucc();
            c.connectAsSucc(first);
            first.connectAsPred(c);
            first = f;

        }
    }

    public int strip() {
        int count = 0;
        Element c = first;
        while (c != null) {
            if(c.getContent() == null) {
                if(c == first) {
                    first = c.getSucc();
                    first.disconnectPred();
                    c = first;
                }else if(c == last) {
                    last = c.getPred();
                    last.disconnectSucc();
                }else{
                    Element b = c.getPred();
                    Element s = c.getSucc();
                    b.connectAsSucc(s);
                    s.connectAsPred(b);
                    c = s;
                }
                count++;
            }
            c = c.getSucc();
        }
        return count;
    }

    public boolean allEqual(DoublyLinkedList<T> d) {
        if(this.size == d.size) {
            Element c1 = first;
            Element c2 = d.first;
            while (c1 != null && c2 != null) {
                if(!c1.getContent().equals(c2.getContent())) return false;
                c1 = c1.getSucc();
                c2 = c2.getSucc();
            }

            return true;
        }else{
            return false;
        }
    }

    public void appendFirst() {
        if(this.size > 1) {
            Element f = first;
            Element s = first.getSucc();
            Element l = first;
            while (l.getSucc() != null) l = l.getSucc();
            l.connectAsSucc(f);
            l.getSucc().connectAsPred(l);
            l.getSucc().disconnectSucc();
            first = s;
            last = l.getSucc();
        }
    }

    public int[] positions() {
        int size = 0;
        Element cur = first;
        while (cur != null) {
            if(cur.getContent() == null) size++;
            cur = cur.getSucc();
        }
        if(size == 0) return new int[0];
        int idx = 0, c = 0;
        int[] r = new int[size];
        cur = first;
        while (cur != null) {
            if(cur.getContent() == null)  r[idx++] = c;
            c++;
            cur = cur.getSucc();
        }
        return r;
    }

    public int splitBehind(T c) {
        Element current = first;
        int newSize = 0;
        while (current != null) {
            if(!current.getContent().equals(c)) {
                current = current.getSucc();
                newSize++;
            }

            else{
                newSize++;
                current.disconnectSucc();
                last = current;
                int r = this.size - newSize;
                this.size = newSize;
                return r;
            }
        }
        return 0;
    }

    public boolean allIn(T[] test) {
        Element c = first;
        while (c != null) {
            boolean found = false;
            for(T v: test) {
                if(v.equals(c.getContent())) {
                    found = true;
                    break;
                }
            }
            if(!found) return false;
            c = c.getSucc();
        }
        return true;
    }

    public void exchange() {
        Element current = first;
        Element firstHit = null;
        while (current != null) {
            if(current.getContent() != null) {
                if(firstHit == null) {
                    firstHit = current;
                    current = current.getSucc();
                }else{
                    T tmp = firstHit.getContent();
                    firstHit.setContent(current.content);
                    current.setContent(tmp);
                    return;
                }
            }else{
                current = current.getSucc();
            }
        }
    }

    public static abstract class DeletionStrategy<E>
    {
        public abstract boolean select( E ref );
    }

    public void deleteSelected( DeletionStrategy<T> s )
    {
        Element current = first;
        while ( current != null )
        {
            Element candidate = current;
            current = current.getSucc(); 
            if ( s.select( candidate.getContent() ) )
            {
                remove( candidate );
            }
        }
    }

    private void remove( Element e )
    {
        if ( e != null ) 
        {
            if ( e.hasSucc() && e.hasPred() )
            {
                e.getPred().connectAsSucc( e.getSucc() );
            } else if ( e == first && e.hasSucc() )
            {
                first = first.getSucc();
                first.disconnectPred();
            } else if ( e == last && e.hasPred() )
            {
                last = last.getPred();
                last.disconnectSucc();
            } else {
                first = last = null;
            }
            size--;
        }
    }

    // Element

    private class Element
    {
        private T content;
        private Element pred, succ;

        public Element( T c )
        {
            content = c;
            pred = succ = null;
        }

        public T getContent()
        {
            return content;
        }

        public void setContent( T c )
        {
            content = c;
        }

        public boolean hasSucc()
        {
            return succ != null;
        }

        public Element getSucc()
        {
            return succ;
        }

        public void disconnectSucc()
        {
            if ( hasSucc() ) 
            {
                succ.pred = null;
                succ = null;
            }


        }
        // 1 2 3 4 5 6 7 8
        //         ^
        public void f(int from) {
            //if zeugs aussen
            Element f = first;
            for(int i = 1; i <= from; i++) {
                f = f.getSucc();
            }
            first = f;
            f.disconnectPred();
        }

        public void into(T test, T into) {
            Element f = first;
            while (f != null) {
                if(f.getContent().equals(test)) {
                    Element k = f.getSucc();
                    Element n = new Element(into);
                    f.connectAsSucc(n);
                    f.getSucc().connectAsPred(f);
                    f.getSucc().connectAsSucc(k);
                    if(k == null) {
                        last = f.getSucc();
                    }else{
                        k.connectAsPred(f.getSucc());
                    }
                }
                f = f.getSucc();
            }
        }

        public void con(DoublyLinkedList<T> par) {
            Element t = par.last;
            if(t.getPred() == null) {
                par.first = null;
                par.last = first;
            }else{
                par.last = par.last.getPred();
                par.last.disconnectSucc();
            }
            if(first == null) {
                first = t;
                last = first;
            }else{
                first.connectAsPred(t);
                first.getPred().connectAsSucc(first);
                first = first.getPred();
                first.disconnectPred();
            }
        }

        public void connectAsSucc( Element e)
        {
            disconnectSucc();
            if ( e != null ) 
            {
                e.disconnectPred();
                e.pred = this;
            }
            succ = e;
        }

        public boolean hasPred()
        {
            return pred != null;
        }

        public Element getPred()
        {
            return pred;
        }

        public void disconnectPred()
        {
            if ( hasPred() )
            {
                pred.succ = null;
                pred = null;

            }
        }

        public void connectAsPred( Element e )
        {
            disconnectPred();
            if ( e != null )
            {
                e.disconnectSucc();
                e.succ = this;
            }
            pred = e;
        }
    }

}
