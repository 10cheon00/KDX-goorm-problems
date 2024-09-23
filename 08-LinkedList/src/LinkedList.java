import java.util.Iterator;


public class LinkedList<T> implements Iterable<T> {
    private final Node head;
    private int size;

    class Node {
        private Node next;
        private final T data;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public T getData() {
            return data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node current = head.getNext();

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T data = current.getData();
                    current = current.getNext();
                    return data;
                }
                return null;
            }
        };
    }

    public LinkedList() {
        this.head = new Node(null);
        size = 0;
    }

    public void add(T t) {
        Node iter = head;
        while (iter.getNext() != null) {
            iter = iter.getNext();
        }
        iter.setNext(new Node(t));
        size++;
    }

    public T get(int index) {
        Node iter = getNode(index);
        if (iter == null) {
            return null;
        }
        return iter.getData();
    }

    public T delete(int index) {
        Node iter = getNode(index - 1);
        if (iter == null || iter.getNext() == null) {
            return null;
        }
        Node removed = iter.getNext();
        iter.setNext(removed.getNext());
        size--;
        return removed.getData();
    }

    private Node getNode(int index) {
        if (index < -1 || index >= size) {
            return null;
        }
        Node iter = head;
        int i = -1;
        while (i < index) {
            iter = iter.getNext();
            i++;
        }
        return iter;
    }
}
