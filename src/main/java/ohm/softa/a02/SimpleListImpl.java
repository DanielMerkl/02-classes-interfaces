package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable<Object> {
    private Element head;
    private int size;

    @Override
    public void add(Object item) {
        if (head == null) {
            head = new Element(item);
        } else {
            Element current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Element(item));
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList filteredList = new SimpleListImpl();
        for (Object current : this) {
            if (filter.include(current)) {
                filteredList.add(current);
            }
        }

        return filteredList;
    }

    @Override
    public Iterator<Object> iterator() {
        return new SimpleIteratorImpl();
    }

    private static class Element {
        private final Object item;
        private Element next = null;

        public Element(Object item) {
            this.item = item;
        }

        public Object getItem() {
            return item;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }

    private class SimpleIteratorImpl implements Iterator<Object> {
        private Element current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object currentValue = current.getItem();
            current = current.getNext();
            return currentValue;
        }
    }
}
