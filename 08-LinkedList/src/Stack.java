import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void push(T value) {
        LinkedList<T> newList = new LinkedList<>();
        newList.add(value);
        for(T t : list) {
            newList.add(t);
        }
        list = newList;
    }

    public T pop() {
        return list.delete(0);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Iterator<T>it = list.iterator();
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public T next() {
                return it.next();
            }
        };
    }
}
